package com.pratama.baseandroid.ui.homepage

import android.content.pm.ActivityInfo
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
class ListNewsFragment : BaseFragmentBinding<FragmentListNewsBinding>(), NewsItem.NewsListener {

    @Inject
    lateinit var listNewsViewModel: ListNewsViewModel

    private val listNewsAdapter = GroupAdapter<GroupieViewHolder>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListNewsBinding =
        FragmentListNewsBinding::inflate

    override fun setupView(binding: FragmentListNewsBinding) = with(binding) {
        // setupRecyclerview
        rvListNews.layoutManager = LinearLayoutManager(requireActivity())
        rvListNews.adapter = listNewsAdapter
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setListener(binding)

        callData()

        listNewsViewModel.uiState().observe(viewLifecycleOwner, { state ->
            when (state) {

                is ListNewsViewModel.ListNewsState.Loading -> {
                    animationView.toVisible()
                    animationView.setGif(GifEnum.LOADING)
                }

                is ListNewsViewModel.ListNewsState.NewsLoaded -> {
                    animationView.toGone()
                    animationView.pauseAnimation()
                    swipeRefreshLayout.isRefreshing = false

                    ThreadInfoLogger.logThreadInfo("show news viewmodel")

                    state.news.map {
                        d { "news loaded -> ${it.title}" }
                        listNewsAdapter.add(NewsItem(it, this@ListNewsFragment))
                    }
                }
            }
        })
        addMenu(binding)
    }

    private fun addMenu(binding: FragmentListNewsBinding) {
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
        menu.select(R.id.news)
        menu.onItemSelectedListener = object :OnItemClickListener{
            override fun invoke(v: View, menuItem: MenuItem, byUser: Boolean) {
                if (menuItem.id == R.id.live) {
                    findNavController().navigate(
                        ListNewsFragmentDirections.actionListNewsFragmentToLiveFragment()
                    )
                    menu.select(R.id.news)
                }
            }

        }
    }


    override fun onNewsSelected(news: News) {
        FinestWebView.Builder(requireContext())
            .setCustomAnimations(R.anim.exit_to_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left)
            .show(news.url)
    }

    private fun setListener(binding: FragmentListNewsBinding) {
        binding.swipeRefreshLayout.setOnRefreshListener {
            listNewsAdapter.clear()

            callData()
        }
    }

    private fun callData() {
        listNewsViewModel.getTopHeadlinesByCountry(country = "us", category = "technology")
    }

}