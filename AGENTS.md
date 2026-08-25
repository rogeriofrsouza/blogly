# Blogly

Blogly is a blogging platform: users sign up, write posts, and comment on posts that have been published. It is a
backend only, and every capability is exposed as an authenticated HTTP endpoint under `/api`.

## Stack

Kotlin 2.3 on Spring Boot 4.0, built with Gradle (Kotlin DSL) against a JDK 25 toolchain. A handful of DTOs and all JPA
entities are Java.

- **Persistence** — Postgres 18 via Spring Data JPA / Hibernate, schema owned by Flyway.
- **Security** — Spring Security with stateless JWT, password hashing through a delegating encoder.
- **API docs** — springdoc with the Scalar UI at `/scalar`, OpenAPI JSON at `/v3/api-docs`.
- **IDs** — using TSIDs.

## Commands

```bash
# Build
./gradlew build
./gradlew compileKotlin

# Run tests
./gradlew test

# Start the app
./gradlew bootRun

# Docker
docker compose up --build
```

## Architecture

### Layers

This project uses Domain Driven Design and Clean Architecture. It's structured in four packages under
`com.blogly.blogly`: domain, application, infrastructure, and presentation. Dependencies point inward only — `domain`
imports nothing from the other three.

**`domain/`** — pure Kotlin, framework-free.

- Aggregates are mutable classes with behavior methods that enforce their own invariants;
- Value Objects validate input on constructors;
- Repository interfaces speak only in domain types;
- Custom exceptions.

**`application/`** — usecases, request/response DTOs, ports.

- One class per use case, with a single execute function;
- Use cases orchestrate: load aggregate, check authorization, call the aggregate's behavior method, save, etc.;
- Define the ports that outer layers implement;
- Request/response DTOs live in `application/<aggregate>/dto/`, with extension functions doing the mapping.

**`infrastructure/`** — adapters, framework, and other dependencies.

- `persistence/<aggregate>/` holds a JPA entity, a Spring Data JPA Repository, a domain repository, and a domain mapper
  object converting entity ↔ domain;
- `security/` holds the JWT filter and security configuration.

**`presentation/`** — REST controllers plus their own wire DTOs.

- Separate from application DTOs;
- Bean Validation annotations;
- OpenAPI annotations;
- Controllers decode the TSID path variable, call the use case, and map back.

### Database

- Flyway migrations in `src/main/resources/db/migration/`;
- `ddl-auto: validate` — schema changes must go through a migration;
- New migrations use minor versions of the current head (`V6.1`, not `V7`).
