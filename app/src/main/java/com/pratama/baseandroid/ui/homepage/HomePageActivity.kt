package com.pratama.baseandroid.ui.homepage

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.github.ajalt.timberkt.d
import com.pratama.baseandroid.R
import com.pratama.baseandroid.coreandroid.base.BaseActivityBinding
import com.pratama.baseandroid.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import github.com.st235.lib_expandablebottombar.MenuItem
import github.com.st235.lib_expandablebottombar.MenuItemDescriptor
import github.com.st235.lib_expandablebottombar.OnItemClickListener

@AndroidEntryPoint
class HomePageActivity : BaseActivityBinding<ActivityHomeBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun setupView(binding: ActivityHomeBinding) {
        d { "setup view" }
    }

}