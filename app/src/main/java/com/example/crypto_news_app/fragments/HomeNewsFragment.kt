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
import com.example.crypto_news_app.models.NewsResponse
import com.example.crypto_news_app.ui.NewsAdapter
import com.example.crypto_news_app.ui.NewsViewModel
import com.example.crypto_news_app.utils.Resource
import kotlinx.android.synthetic.main.fragment_home_news.*

class HomeNewsFragment: Fragment(R.layout.fragment_home_news) {

    val myAdapter by lazy { NewsAdapter() }
    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        setUpRecyclerView()

        myAdapter.setOnItemClickListener {
            //Puts the article data into bundle
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }

            //
            view.findNavController().navigate(
                R.id.action_homeNewsFragment_to_articleFragment, bundle)

        }

        //viewModel.getBreakingNews( 1, "blockchain")
        viewModel.breakingNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        myAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(activity, response.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    Toast.makeText(activity, "LOADING", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun setUpRecyclerView() {
        rvHomeNews.adapter = myAdapter
        rvHomeNews.layoutManager = LinearLayoutManager(activity)
    }

}