package com.pratama.baseandroid.data.datasource.remote.model

import com.pratama.baseandroid.domain.entity.News

data class TopHeadlineResponse(
    val status: String,
    val totalResults: Int,
    val news: List<NewsResponse>
)

fun TopHeadlineResponse.toNewsList(): List<News> {
    val listNews = mutableListOf<News>()
    news.map {
        listNews.add(toNews((it)))
    }
    return listNews
}

