FROM eclipse-temurin:25-jdk AS deps
WORKDIR /builder
COPY --chmod=0755 gradlew gradlew
COPY gradle/ gradle/
RUN --mount=type=bind,source=build.gradle.kts,target=build.gradle.kts \
    --mount=type=bind,source=settings.gradle.kts,target=settings.gradle.kts \
    --mount=type=cache,target=/root/.gradle \
    ./gradlew dependencies --no-daemon

FROM deps AS package
WORKDIR /builder
COPY ./src src/
RUN --mount=type=bind,source=build.gradle.kts,target=build.gradle.kts \
    --mount=type=bind,source=settings.gradle.kts,target=settings.gradle.kts \
    --mount=type=cache,target=/root/.gradle \
    ./gradlew bootJar --no-daemon

FROM package AS extract
WORKDIR /builder
RUN java -Djarmode=tools -jar build/libs/*.jar extract --layers --launcher --destination build/extracted

FROM extract AS development
WORKDIR /builder
RUN cp -r build/extracted/dependencies/. ./
RUN cp -r build/extracted/spring-boot-loader/. ./
RUN cp -r build/extracted/snapshot-dependencies/. ./
RUN cp -r build/extracted/application/. ./
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
CMD [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]

FROM eclipse-temurin:25-jre AS final
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser
COPY --from=extract builder/build/extracted/dependencies/ ./
COPY --from=extract builder/build/extracted/spring-boot-loader/ ./
COPY --from=extract builder/build/extracted/snapshot-dependencies/ ./
COPY --from=extract builder/build/extracted/application/ ./
EXPOSE 8080
ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]