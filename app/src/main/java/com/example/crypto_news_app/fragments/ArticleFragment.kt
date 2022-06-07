package com.example.crypto_news_app.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.crypto_news_app.NewsActivity
import com.example.crypto_news_app.R
import com.example.crypto_news_app.models.Article
import com.example.crypto_news_app.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment: Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val arg: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        val article = arg.article



        webView.apply {
            webViewClient = WebViewClient()
            settings.supportZoom()
            loadUrl(article.url)
        }

        fab.setOnClickListener {
            viewModel.insertNews(article)
            Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show()
        }

    }
}