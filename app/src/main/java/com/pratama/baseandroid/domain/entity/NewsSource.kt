package com.pratama.baseandroid.domain.entity

import com.pratama.baseandroid.ui.dto.NewsSourceDto

data class NewsSource(
    val news: List<News>,
    val status: String
)

fun NewsSource.toDto(): NewsSourceDto {
    return NewsSourceDto(news, status)
}