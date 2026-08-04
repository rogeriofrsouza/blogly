package com.blogly.blogly.infrastructure.shared

import com.blogly.blogly.application.shared.IdProvider
import io.hypersistence.tsid.TSID
import org.springframework.stereotype.Component

@Component
class TsidIdProvider : IdProvider {

    override fun generate(): Long = TSID.fast().toLong()

    override fun encode(value: Long): String = TSID(value).toLowerCase()

    override fun decode(value: String): Long = TSID.from(value).toLong()
}
