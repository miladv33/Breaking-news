package com.pratama.baseandroid.ui.homepage

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ajalt.timberkt.d
import com.pratama.baseandroid.R
import com.pratama.baseandroid.coreandroid.base.BaseFragmentBinding
import com.pratama.baseandroid.coreandroid.extensions.toGone
import com.pratama.baseandroid.coreandroid.extensions.toVisible
import com.pratama.baseandroid.databinding.FragmentListNewsBinding
import com.pratama.baseandroid.databinding.FragmentLiveBinding
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.domain.entity.toDto
import com.pratama.baseandroid.ui.homepage.rvitem.NewsItem
import com.pratama.baseandroid.utility.ThreadInfoLogger
import com.pratama.baseandroid.utility.lottie.BindingLottie.setGif
import com.pratama.baseandroid.utility.lottie.GifEnum
import com.thefinestartist.finestwebview.FinestWebView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import github.com.st235.lib_expandablebottombar.MenuItem
import github.com.st235.lib_expandablebottombar.MenuItemDescriptor
import github.com.st235.lib_expandablebottombar.OnItemClickListener
import render.animations.Bounce
import javax.inject.Inject

@AndroidEntryPoint
class LiveFragment : BaseFragmentBinding<FragmentLiveBinding>(), NewsItem.NewsListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLiveBinding =
        FragmentLiveBinding::inflate

    override fun setupView(binding: FragmentLiveBinding) = with(binding) {
        // setupRecyclerview
        rvListNews.layoutManager = LinearLayoutManager(requireActivity())

        setListener(binding)

        callData()

        addMenu(binding)

    }

    private fun addMenu(binding: FragmentLiveBinding) {
        val menu = binding.expandableBottomBar.menu
        menu.add(
            MenuItemDescriptor.Builder(
                requireContext(),
                itemId = R.id.news,
                R.drawable.ic_news,
                textId = R.string.news,
                Color.YELLOW
            ).build()
        )
        menu.add(
            MenuItemDescriptor.Builder(
                requireContext(),
                itemId = R.id.live,
                R.drawable.ic_live,
                textId = R.string.live,
                Color.RED
            ).build()
        )

        menu.onItemSelectedListener = object : OnItemClickListener {
            override fun invoke(v: View, menuItem: MenuItem, byUser: Boolean) {
                if (menuItem.id == R.id.news) {
                    findNavController().navigate(
                        LiveFragmentDirections.actionLiveFragmentToListNewsFragment()
                    )
                }
            }
        }
        menu.select(R.id.live)
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
        binding.swipeRefreshLayout.setOnRefreshListener {
            callData()
        }
    }

    private fun callData() {

    }

}