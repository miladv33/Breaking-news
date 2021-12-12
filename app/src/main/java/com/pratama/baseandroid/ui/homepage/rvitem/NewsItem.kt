package com.pratama.baseandroid.ui.homepage.rvitem

import android.view.View
import com.pratama.baseandroid.R
import com.pratama.baseandroid.coreandroid.extensions.loadFromUrl
import com.pratama.baseandroid.databinding.RvItemNewsBinding
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.utility.getNewsDate
import com.xwray.groupie.viewbinding.BindableItem

class NewsItem(private val news: News, val listener: NewsItem.NewsListener) :
    BindableItem<RvItemNewsBinding>() {

    interface NewsListener {
        fun onNewsSelected(news: News)
    }

    override fun bind(viewBinding: RvItemNewsBinding, position: Int) = with(viewBinding) {
        newsImage.loadFromUrl(news.image)
        titleTextView.text = news.title
        descriptionTextView.text = news.description
        dateTextView.text = getNewsDate(news.published)
        this.root.setOnClickListener { listener.onNewsSelected(news) }
    }

    override fun getLayout(): Int = R.layout.rv_item_news

    override fun initializeViewBinding(view: View): RvItemNewsBinding = RvItemNewsBinding.bind(view)

}