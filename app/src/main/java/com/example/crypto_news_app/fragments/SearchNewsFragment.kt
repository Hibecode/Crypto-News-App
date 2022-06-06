package com.example.crypto_news_app.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypto_news_app.NewsActivity
import com.example.crypto_news_app.R
import com.example.crypto_news_app.ui.NewsAdapter
import com.example.crypto_news_app.ui.NewsViewModel
import com.example.crypto_news_app.utils.Resource
import kotlinx.android.synthetic.main.fragment_home_news.*
import kotlinx.android.synthetic.main.fragment_search_news.*

class SearchNewsFragment: Fragment(R.layout.fragment_search_news) {

    val myAdapter by lazy { NewsAdapter() }
    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        setUpRecyclerView()

        myAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("article", it)
            }

            view.findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment, bundle)
        }



        var searchMessage = etSearchNews.text
        var query = ""
        viewModel.getSearchNews(1 , query)
        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.articles.let { myAdapter.differ.submitList(it) }
                }
                is Resource.Error -> {
                    Toast.makeText(activity, response.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    Toast.makeText(activity, "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        })


    }


    private fun setUpRecyclerView() {
        rvHomeNews.adapter = myAdapter
        rvHomeNews.layoutManager = LinearLayoutManager(activity)
    }
}