package com.example.crypto_news_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val navHostFrag = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFrag.navController
        bottomNavigationView.setupWithNavController(navController)

        val db = ArticleDB(this)
        val repository = NewsRepository(db)
        val viewModelFactory = NewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)


    }

}