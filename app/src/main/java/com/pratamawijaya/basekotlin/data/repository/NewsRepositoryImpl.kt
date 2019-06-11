package com.pratamawijaya.basekotlin.data.repository

import com.pratamawijaya.basekotlin.data.mapper.ArticleMapper
import com.pratamawijaya.basekotlin.data.services.NewsServices
import com.pratamawijaya.basekotlin.domain.Article
import io.reactivex.Single

class NewsRepositoryImpl(private val service: NewsServices,
                         private val articleMapper: ArticleMapper) : NewsRepository {

    override fun getArticles(): Single<List<Article>> {
        return service.getTopHeadlines("id")
                .map { articleMapper.mapToListDomain(it.articles) }
    }
}