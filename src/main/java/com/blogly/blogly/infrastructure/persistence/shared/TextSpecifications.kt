package com.blogly.blogly.infrastructure.persistence.shared

import org.springframework.data.jpa.domain.Specification

object TextSpecifications {

    private const val ESCAPE_CHARACTER = '\\'

    private val SPECIAL_CHARACTERS = setOf(ESCAPE_CHARACTER, '%', '_')

    fun <T : Any> containsIgnoreCase(attribute: String, term: String) = Specification<T> { root, _, builder ->
        builder.like(
            builder.lower(root.get(attribute)), "%${escape(term.lowercase())}%", ESCAPE_CHARACTER
        )
    }

    private fun escape(term: String) = buildString {
        term.forEach { character ->
            if (character in SPECIAL_CHARACTERS) append(ESCAPE_CHARACTER)
            append(character)
        }
    }
}
