package com.example.crypto_news_app.fragments

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
import kotlinx.coroutines.*

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
        viewModel.getSearchNews(query)
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

        var job: Job? = null
        etSearchNews.addTextChangedListener{ editable ->
            //Cancel the current coroutine
            job?.cancel()
            // Launch a new coroutine
            job = MainScope().launch {
                //add delay to allow incase text changes in less than half a second
                delay(500L)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.getSearchNews(editable.toString())
                    }
                }
            }
        }


    }


    private fun setUpRecyclerView() {
        rvSearchNews.adapter = myAdapter
        rvSearchNews.layoutManager = LinearLayoutManager(activity)
    }
}