package com.pratama.baseandroid.utility

import android.os.Handler
import android.os.Looper
import android.view.View

fun View.show(): View {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            if (visibility != View.VISIBLE) {
                visibility = View.VISIBLE
            }
        }
    } else {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
    }
    return this
}

fun View.hide(): View {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            if (visibility != View.INVISIBLE) {
                visibility = View.INVISIBLE
            }
        }
    } else {
        if (visibility != View.INVISIBLE) {
            visibility = View.INVISIBLE
        }
    }
    return this
}

fun View.remove(): View {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            if (visibility != View.GONE) {
                visibility = View.GONE
            }
        }
    } else {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
    }
    return this
}
