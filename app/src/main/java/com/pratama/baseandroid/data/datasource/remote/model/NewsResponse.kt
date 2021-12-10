package com.pratama.baseandroid.data.datasource.remote.model

import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.domain.entity.NewsSource

data class NewsResponse(
    val author: String,
    val category: List<String>,
    val description: String,
    val id: String,
    val image: String,
    val language: String,
    val published: String,
    val title: String,
    val url: String
)

data class SourceResponse(
    val news: List<News>,
    val status: String
)


fun toNews(newsResponse: NewsResponse): News {
    return News(
        author = newsResponse.author ?: "",
        title = newsResponse.title ?: "",
        description = newsResponse.description ?: "",
        url = newsResponse.url ?: "",
        category = newsResponse.category,
        id = newsResponse.id,
        image = newsResponse.image,
        language = newsResponse.language,
        published = newsResponse.published
    )
}

fun toNewsSource(source: SourceResponse): NewsSource {
    return NewsSource(
        news = source.news,
        status = source.status
    )
}