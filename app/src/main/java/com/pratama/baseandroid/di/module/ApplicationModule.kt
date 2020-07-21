package com.pratama.baseandroid.di.module

import android.content.Context
import com.pratama.baseandroid.BuildConfig
import com.pratama.baseandroid.coreandroid.network.NetworkChecker
import com.pratama.baseandroid.coreandroid.network.NetworkCheckerImpl
import com.pratama.baseandroid.data.datasource.local.NewsLocalDatasource
import com.pratama.baseandroid.data.datasource.local.NewsLocalDatasourceImpl
import com.pratama.baseandroid.data.datasource.remote.NewsRemoteDatasource
import com.pratama.baseandroid.data.datasource.remote.NewsRemoteDatasourceImpl
import com.pratama.baseandroid.data.datasource.remote.service.NewsApiServices
import com.pratama.baseandroid.data.repository.NewsRepository
import com.pratama.baseandroid.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                provideHeaderInterceptor(chain)
            }
            .build()
    } else {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                provideHeaderInterceptor(chain)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideNewsApiServices(retrofit: Retrofit): NewsApiServices =
        retrofit.create(NewsApiServices::class.java)

    @Provides
    @Singleton
    fun provideNewsRemoteDatasource(services: NewsApiServices): NewsRemoteDatasource {
        return NewsRemoteDatasourceImpl(services)
    }

    @Provides
    @Singleton
    fun provideNewsLocalDatasource(): NewsLocalDatasource {
        return NewsLocalDatasourceImpl()
    }

    @Provides
    @Singleton
    fun provideNetworkChecker(@ApplicationContext ctx: Context): NetworkChecker {
        return NetworkCheckerImpl(ctx)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        remote: NewsRemoteDatasource,
        local: NewsLocalDatasource,
        networkCheck: NetworkChecker
    ): NewsRepository {
        return NewsRepositoryImpl(remote = remote, local = local, networkChecker = networkCheck)
    }

    private fun provideHeaderInterceptor(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Api-Key", "4b4df2ea3a154950852b6fda536cfb7f").build()
        return chain.proceed(request)
    }
}