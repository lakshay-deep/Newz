package com.lakshay.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lakshay.newsapp.data.model.Article
import com.lakshay.newsapp.databinding.CellNewsBinding
import com.lakshay.newsapp.ui.viewHolders.NewsViewHolder

class NewsAdapter(
    private val onNewsClick : (article: Article) -> Unit
) : ListAdapter<Article, NewsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = CellNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, onNewsClick)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position).let { article ->
            holder.setNews(article)
        }
    }
}