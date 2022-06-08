package com.example.crypto_news_app.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto_news_app.R
import com.example.crypto_news_app.models.Article
import com.example.crypto_news_app.models.Tag
import com.example.crypto_news_app.ui.NewsViewModel
import kotlinx.android.synthetic.main.item_tag.view.*

class HorizontalScrollAdapter: RecyclerView.Adapter<HorizontalScrollAdapter.HorizontalScrollViewHolder>() {

    lateinit var viewModel: NewsViewModel
    inner class HorizontalScrollViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalScrollViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag,parent,false)

        return HorizontalScrollViewHolder(view)
    }

    override fun getItemCount() = differ.currentList.size


    override fun onBindViewHolder(holder: HorizontalScrollViewHolder, position: Int) {
        val tag = differ.currentList[position]
        holder.itemView.apply {
            tagButton.text = tag.name
            // This changes color of tag button depending on whether is pressed or not
            tagButton.setOnClickListener {
                if (tag.isPressed){
                    viewModel.getBreakingNews(1, "blockchain")
                    tagButton.setBackgroundColor(resources.getColor(R.color.tagNotPressed))
                    tag.isPressed = false
                } else {
                    viewModel.getBreakingNews(1, tag.name)
                    tagButton.setBackgroundColor(resources.getColor(R.color.tagPressed))
                    tag.isPressed = true
                }
            }


        }
    }
}