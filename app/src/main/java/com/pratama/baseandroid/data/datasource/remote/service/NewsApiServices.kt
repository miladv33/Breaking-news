package com.pratama.baseandroid.data.datasource.remote.service

import com.pratama.baseandroid.data.datasource.remote.model.TopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiServices {

    @GET("latest-news")
    suspend fun getTopHeadlines(@Query("apiKey") key:String): TopHeadlineResponse
}