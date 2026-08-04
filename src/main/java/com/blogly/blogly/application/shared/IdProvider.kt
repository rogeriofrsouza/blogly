package com.blogly.blogly.application.shared

interface IdProvider {

    fun generate(): Long

    fun encode(value: Long): String

    fun decode(value: String): Long
}
