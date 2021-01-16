package com.pratama.baseandroid.ui.homepage

import android.view.LayoutInflater
import com.pratama.baseandroid.coreandroid.base.BaseActivityBinding
import com.pratama.baseandroid.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageActivity : BaseActivityBinding<ActivityHomeBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun setupView(binding: ActivityHomeBinding) {

    }


}