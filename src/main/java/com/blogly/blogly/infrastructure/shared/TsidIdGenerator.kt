package com.blogly.blogly.infrastructure.shared

import com.blogly.blogly.application.shared.IdGenerator
import io.hypersistence.tsid.TSID
import org.springframework.stereotype.Component

@Component
class TsidIdGenerator : IdGenerator {

    override fun generate(): Long = TSID.fast().toLong()
}
