package com.pratama.baseandroid.domain.entity

import com.pratama.baseandroid.data.datasource.local.entity.NewsEntity
import com.pratama.baseandroid.ui.dto.NewsDto

data class News(
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
    fun News.toNewsEntity(): NewsEntity {
        return NewsEntity(
            title = this.title,
            author = this.author,
            description = this.description,
            url = this.url,
            category = this.category,
            id = this.id,
            image = this.image,
            language = this.language,
            published = this.published,
        )
    }
    fun News.toDto(): NewsDto {
        return with(this) {
            NewsDto(author, description, id, image, language, published, title, url)
        }
    }
