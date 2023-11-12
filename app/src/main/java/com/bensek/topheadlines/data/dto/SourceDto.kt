package com.bensek.topheadlines.data.dto

import com.bensek.topheadlines.domain.model.Source

data class SourceDto(
    val id: String?,
    val name: String,
    val description: String?,
    val url: String?,
    val category: String?,
    val language: String?,
    val country: String?,
)

fun SourceDto.toSource(): Source {
    return Source(
        id = id ?: "",
        name = name
    )
}

