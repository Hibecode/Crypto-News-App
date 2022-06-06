package com.example.crypto_news_app.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypto_news_app.NewsActivity
import com.example.crypto_news_app.R
import com.example.crypto_news_app.ui.NewsAdapter
import com.example.crypto_news_app.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_home_news.*
import kotlinx.android.synthetic.main.fragment_saved_news.*

class SavedNewsFragment: Fragment(R.layout.fragment_saved_news) {

    val myAdapter by lazy { NewsAdapter() }
    lateinit var viewModel: NewsViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
        rvSavedNews.adapter = myAdapter
        rvSavedNews.layoutManager = LinearLayoutManager(activity)
    }
}