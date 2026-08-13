package com.blogly.blogly.infrastructure.persistence

import com.blogly.blogly.domain.shared.EntityId
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import java.sql.Timestamp
import kotlin.reflect.full.primaryConstructor
import kotlin.time.Instant
import kotlin.time.toJavaInstant
import kotlin.time.toKotlinInstant

@Configuration
class JdbcConfiguration : AbstractJdbcConfiguration() {
    override fun userConverters(): List<*> = listOf(
        EntityIdToLongConverter(),
        LongToEntityIdConverterFactory(),
        InstantToTimestampConverter(),
        TimestampToInstantConverter()
    )
}

@WritingConverter
class EntityIdToLongConverter : Converter<EntityId, Long> {
    override fun convert(source: EntityId): Long = source.value
}

@ReadingConverter
class LongToEntityIdConverterFactory : ConverterFactory<Long, EntityId> {
    override fun <T : EntityId> getConverter(targetType: Class<T>): Converter<Long, T> {
        val constructor = targetType.kotlin.primaryConstructor
            ?: throw IllegalStateException("No primary constructor found for $targetType")

        return Converter { source -> constructor.call(source) }
    }
}

@WritingConverter
class InstantToTimestampConverter : Converter<Instant, Timestamp> {
    override fun convert(source: Instant): Timestamp = Timestamp.from(source.toJavaInstant())
}

@ReadingConverter
class TimestampToInstantConverter : Converter<Timestamp, Instant> {
    override fun convert(source: Timestamp): Instant = source.toInstant().toKotlinInstant()
}
