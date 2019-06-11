package com.pratamawijaya.basekotlin.data.services

import com.pratamawijaya.basekotlin.data.response.TopHeadlineResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String): Single<TopHeadlineResponse>
}