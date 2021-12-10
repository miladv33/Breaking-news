package com.pratama.baseandroid.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.domain.entity.NewsSource
import retrofit2.http.Field

@Entity(tableName = "NewsEntity")
data class NewsEntity(
    val author: String,
    val category: List<String>,
    val description: String,
    val id: String,
    val image: String,
    val language: String,
    val published: String,
    val title: String,
    val url: String,
    @PrimaryKey(autoGenerate = true)
    var newsId: Int = 0
) {
    constructor() : this("", listOf(), "", "", "", "", "", "", "", 0)
}

fun NewsEntity.toNews(): News {
    return News(
        id = this.id,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        published = this.published,
        category = this.category,
        image = this.url,
        language = this.language
    )
}
