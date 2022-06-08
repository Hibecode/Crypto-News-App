package com.example.crypto_news_app.adapters

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
import kotlinx.android.synthetic.main.item_tag.view.*

class HorizontalScrollAdapter: RecyclerView.Adapter<HorizontalScrollAdapter.HorizontalScrollViewHolder>() {


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
            if (tag.isPressed){
                tagButton.setOnClickListener { tagButton ->
                    tagButton.setBackgroundColor(808080)
                }
            } else {
                tag.isPressed = false
                tagButton.setBackgroundColor("3F66B5")
            }

        }
    }
}