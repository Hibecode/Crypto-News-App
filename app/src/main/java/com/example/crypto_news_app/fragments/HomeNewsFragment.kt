package com.example.crypto_news_app.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.crypto_news_app.NewsActivity
import com.example.crypto_news_app.R
import com.example.crypto_news_app.adapters.HorizontalScrollAdapter
import com.example.crypto_news_app.adapters.NewsAdapter
import com.example.crypto_news_app.models.Tag
import com.example.crypto_news_app.ui.NewsViewModel
import com.example.crypto_news_app.utils.Resource
import com.example.crypto_news_app.utils.parseDateTime
import kotlinx.android.synthetic.main.fragment_home_news.*

class HomeNewsFragment: Fragment(R.layout.fragment_home_news) {

    val myAdapter by lazy { NewsAdapter() }
    val TagAdapter by lazy { HorizontalScrollAdapter() }
    lateinit var viewModel: NewsViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel



        setUpRecyclerView()
        setUpTagRecyclerView()
        TagAdapter.viewModel = viewModel
        rvHomeNews.setHasFixedSize(false)

        myAdapter.setOnItemClickListener {
            //Puts the article data into bundle
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }


            view.findNavController().navigate(
                R.id.action_homeNewsFragment_to_articleFragment, bundle)

        }

        //viewModel.getBreakingNews( 1, "blockchain")
        viewModel.breakingNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        Glide.with(this).load(newsResponse.articles[0].urlToImage).into(ivMainImage)
                        tvMainTitle.text = newsResponse.articles[0].title
                        myAdapter.differ.submitList(newsResponse.articles.drop(1))
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

    private fun setUpTagRecyclerView() {
        rvHorizontalScroll.adapter = TagAdapter
        rvHorizontalScroll.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        TagAdapter.differ.submitList(TagList())
    }

    private fun TagList() =
        listOf<Tag>(
            Tag("Blockchain"),
            Tag("Bitcoin"),
            Tag("Ethereum"),
            Tag("Altcoin"),
            Tag("Crypto"),
            Tag("DAO"),
            Tag("P2P"),
            Tag("dApp"),
            Tag("NFT"),
            Tag("Solidity"),
            Tag("Token")
        )


}