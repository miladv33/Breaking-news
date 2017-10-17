package com.pratamawijaya.basekotlin.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pratamawijaya.basekotlin.BuildConfig
import com.pratamawijaya.basekotlin.di.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by pratama on 8/4/17.
 */
@Module
class AppModule(val app: Application) {

    @Provides
    fun provideApplication(): Application = app

    @Provides
    @ApplicationContext
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
    }
}