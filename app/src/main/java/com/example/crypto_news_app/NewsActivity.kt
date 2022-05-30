package com.example.crypto_news_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypto_news_app.data.NewsRepository
import com.example.crypto_news_app.db.ArticleDB
import com.example.crypto_news_app.models.Article
import com.example.crypto_news_app.ui.NewsAdapter
import com.example.crypto_news_app.ui.NewsViewModel
import com.example.crypto_news_app.ui.NewsViewModelFactory
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.fragment_home_news.*

class NewsActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private val myAdapter by lazy { NewsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

        setUpRecyclerView()

        val db = ArticleDB(this)
        val repository = NewsRepository(db)
        val viewModelFactory = NewsViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)



    }

    fun setUpRecyclerView() {
        rvHomeNews.adapter = myAdapter
        rvHomeNews.layoutManager = LinearLayoutManager(this)
    }

}