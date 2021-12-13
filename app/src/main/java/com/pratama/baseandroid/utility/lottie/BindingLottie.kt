package com.pratama.baseandroid.utility.lottie

import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

object BindingLottie {

    @BindingAdapter("app:setGif")
    @JvmStatic
    fun LottieAnimationView.setGif(gifName: GifEnum?) {
        setAnimation(gifName?.value ?: GifEnum.LOADING.value)
        playAnimation()
    }
}
