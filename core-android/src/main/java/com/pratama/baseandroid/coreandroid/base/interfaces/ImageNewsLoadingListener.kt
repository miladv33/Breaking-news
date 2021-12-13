package com.pratama.baseandroid.coreandroid.base.interfaces

import java.lang.Exception

interface ImageNewsLoadingListener {
    fun onSuccess(url: String)
    fun onError(e: Exception?)
}