package com.pratama.baseandroid.coreandroid.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import com.pratama.baseandroid.coreandroid.base.interfaces.ImageNewsLoadingListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun View.toVisible() {
    this.visibility = VISIBLE
}

fun View.toGone() {
    this.visibility = GONE
}

fun ImageView.loadFromUrl(url: String, imageNewsLoadingListener: ImageNewsLoadingListener? = null) {
    if (url.isEmpty()) return
    imageNewsLoadingListener?.let {
        Picasso.get().load(url).into(this, object : Callback {
            override fun onSuccess() {
                imageNewsLoadingListener.onSuccess(url)
            }

            override fun onError(e: Exception?) {
                imageNewsLoadingListener.onError(e)
            }

        })
    } ?: run {
        Picasso.get().load(url).into(this)
    }
}