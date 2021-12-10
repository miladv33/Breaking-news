package com.pratama.baseandroid.ui.detailpage

import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.fragment.navArgs
import com.pratama.baseandroid.R
import com.pratama.baseandroid.coreandroid.base.BaseFragmentBinding
import com.pratama.baseandroid.databinding.FragmentDetailNewsBinding
import com.pratama.baseandroid.ui.dto.NewsDto
import com.pratama.baseandroid.utility.hide
import com.pratama.baseandroid.utility.remove
import com.pratama.baseandroid.utility.setSetting
import com.pratama.baseandroid.utility.show

class DetailNewsFragment : BaseFragmentBinding<FragmentDetailNewsBinding>() {

    private val args: DetailNewsFragmentArgs by navArgs()

    private var newsDto: NewsDto? = null
    private var openingLink = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsDto = args.newsDto
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailNewsBinding
        get() = FragmentDetailNewsBinding::inflate

    override fun setupView(binding: FragmentDetailNewsBinding) {
        with(binding) {
            newsDto?.let {
                newsTitle.text = it.title
                contentWebView.setSetting()
                contentWebView.hide()
                val newsUrl = it.url
                contentWebView.loadUrl(newsUrl)
                contentWebView.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        if (!openingLink) {
                            openingLink = true
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                val browserIntent = Intent(Intent.ACTION_VIEW, request?.url)
                                startActivity(browserIntent)
                            }
                        }
                        return false
                    }

                    override fun onReceivedSslError(
                        view: WebView?,
                        handler: SslErrorHandler?,
                        error: SslError?
                    ) {
                        handler?.proceed()
                    }
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        loadingIndicator.remove()
                        view?.show()
                    }

                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        super.onReceivedError(view, request, error)
                    }
                }
                contentWebView.setDownloadListener { url, _, _, _, _ ->
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                }
            }
        }
    }
}