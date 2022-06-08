package com.example.crypto_news_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crypto_news_app.R
import com.example.crypto_news_app.models.Article
import com.example.crypto_news_app.ui.NewsViewModel
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    lateinit var viewModel: NewsViewModel

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,
        parent, false)

        return ArticleViewHolder(view)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        Glide.with(holder.itemView).load(article.urlToImage).into(holder.itemView.ivArticleImage)
        holder.itemView.apply{
            //attach data to View
            tvTitle.text = article.title
            tvSource.text = article.source.name
            tvPublishedAt.text = article.publishedAt

            //Once the item view is clicked the item article data is passed to the
            //onItemClickListener. Continue down
            setOnClickListener{
                //onItemClickListener?.let { it(article) }
                onItemClickListener?.let { it(article) }
            }
            ivArticleImage.setOnClickListener {
                viewModel.deleteNews(article)
            }
        }
    }

    //This is used to store the item article data
    private var onItemClickListener: ((Article) -> Unit)? = null

    //This function takes whatever is called on it in the fragment(which is the listener)
    //and assigns it to the onItemClickListener (which later gets the article parameter.
    fun setOnItemClickListener(listener: ((Article) -> Unit)) {
        onItemClickListener = listener
    }


}