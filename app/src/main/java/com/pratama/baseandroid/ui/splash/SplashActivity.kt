package com.pratama.baseandroid.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.testing.launchFragment
import com.pratama.baseandroid.R
import com.pratama.baseandroid.coreandroid.base.BaseActivityBinding
import com.pratama.baseandroid.databinding.ActivityHomeBinding
import com.pratama.baseandroid.databinding.ActivitySplashBinding
import com.pratama.baseandroid.ui.homepage.HomePageActivity
import kotlinx.coroutines.*
import render.animations.Attention
import render.animations.Bounce
import render.animations.Fade
import render.animations.Render

class SplashActivity : BaseActivityBinding<ActivitySplashBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding
        get() = ActivitySplashBinding::inflate

    override fun setupView(binding: ActivitySplashBinding) {
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                animateViews(binding)
            }
            delay(3000)
            startActivity(Intent(this@SplashActivity, HomePageActivity::class.java))
            finish()
        }
    }

    fun animateViews(binding: ActivitySplashBinding) {
        val render = Render(this)
        // Set Animation
        render.setAnimation(Fade().In(binding.imageView))
        render.setAnimation(Fade().In(binding.imageView2))
        render.start()
    }
}