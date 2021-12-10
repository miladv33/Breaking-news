package com.pratama.baseandroid.utility

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.view.ViewParent
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.cardview.widget.CardView

@SuppressLint("SetJavaScriptEnabled")
fun WebView.setSetting() {
    with(settings) {
        javaScriptEnabled = true
        domStorageEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        loadsImagesAutomatically = true
    }
}

fun WebView.destroyWebView() {
    setLayerType(View.LAYER_TYPE_NONE, null)
    try {
        val parent: ViewParent = parent
        (parent as CardView).removeView(this)
        stopLoading()
        loadUrl("about:blank")
        destroy()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
