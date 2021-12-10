package com.pratama.baseandroid.data.datasource.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("apiKey", "DLopP7R8XGgIGYGE_eilN0Pfukphag3_WzDy5Ko6VN7xyKh4").build()
        return chain.proceed(request)
    }
}
