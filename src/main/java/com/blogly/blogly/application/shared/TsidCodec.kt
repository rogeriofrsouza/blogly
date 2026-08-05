package com.blogly.blogly.application.shared

import io.hypersistence.tsid.TSID

object TsidCodec {

    fun encode(value: Long): String = TSID(value).toLowerCase()

    fun decode(value: String): Long = TSID.from(value).toLong()
}
