package com.pratama.baseandroid.ui.homepage.rvitem

import android.view.View
import com.pratama.baseandroid.R
import com.pratama.baseandroid.coreandroid.base.interfaces.ImageNewsLoadingListener
import com.pratama.baseandroid.coreandroid.extensions.loadFromUrl
import com.pratama.baseandroid.databinding.RvItemNewsBinding
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.utility.getNewsDate
import com.pratama.baseandroid.utility.lottie.BindingLottie.setGif
import com.pratama.baseandroid.utility.lottie.GifEnum
import com.pratama.baseandroid.utility.remove
import com.xwray.groupie.viewbinding.BindableItem
import java.lang.Exception


class NewsItem(private val news: News, val listener: NewsItem.NewsListener) :
    BindableItem<RvItemNewsBinding>() {

    interface NewsListener {
        fun onNewsSelected(news: News)
    }

    override fun bind(viewBinding: RvItemNewsBinding, position: Int) = with(viewBinding) {
        animationView.setGif(GifEnum.LOAD_IMAGE)
        newsImage.loadFromUrl(news.image, object : ImageNewsLoadingListener {
            override fun onSuccess(url: String) {
                if (url == news.image)
                    animationView.remove()
            }

            override fun onError(e: Exception?) {
            }

        })
        titleTextView.text = news.title
        descriptionTextView.text = news.description
        dateTextView.text = getNewsDate(news.published)
        this.root.setOnClickListener { listener.onNewsSelected(news) }
    }

    override fun getLayout(): Int = R.layout.rv_item_news

    override fun initializeViewBinding(view: View): RvItemNewsBinding = RvItemNewsBinding.bind(view)

}