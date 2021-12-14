package com.pratama.baseandroid.ui.homepage

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pratama.baseandroid.R
import com.pratama.baseandroid.coreandroid.base.BaseFragmentBinding
import com.pratama.baseandroid.databinding.FragmentLiveBinding
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.ui.homepage.rvitem.NewsItem
import com.thefinestartist.finestwebview.FinestWebView
import dagger.hilt.android.AndroidEntryPoint
import github.com.st235.lib_expandablebottombar.MenuItem
import github.com.st235.lib_expandablebottombar.MenuItemDescriptor
import github.com.st235.lib_expandablebottombar.OnItemClickListener

@AndroidEntryPoint
class LiveFragment : BaseFragmentBinding<FragmentLiveBinding>(), NewsItem.NewsListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLiveBinding =
        FragmentLiveBinding::inflate

    override fun setupView(binding: FragmentLiveBinding) = with(binding) {
        // setupRecyclerview
        setListener(binding)


        callData()

        addlive(binding)

    }

    private fun addlive(binding: FragmentLiveBinding) {
        binding.andExoPlayerView.setSource("https://reuters-reuters-1-eu.rakuten.wurl.com/manifest/playlist.m3u8")
    }


    override fun onNewsSelected(news: News) {
        FinestWebView.Builder(requireContext())
            .setCustomAnimations(
                R.anim.exit_to_right,
                R.anim.exit_to_left,
                R.anim.enter_from_right,
                R.anim.exit_to_left
            )
            .show(news.url)
    }

    private fun setListener(binding: FragmentLiveBinding) {
       
    }

    private fun callData() {

    }

}