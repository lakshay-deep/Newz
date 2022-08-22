package com.lakshay.newsapp.ui.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lakshay.newsapp.R
import com.lakshay.newsapp.data.model.Article
import com.lakshay.newsapp.data.model.News
import com.lakshay.newsapp.databinding.CellNewsBinding
import com.lakshay.newsapp.util.DateTimeUtil

class NewsViewHolder(
    private val binding: CellNewsBinding,
    private val onNewsClick: (article: Article) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private var article: Article? = null

    init {
        binding.root.setOnClickListener{
            article?.let(onNewsClick)
        }
    }

    fun setNews(article: Article){
        this.article = article
        binding.tvTitle.text = article.title
        binding.tvDescription.text = article.description
        binding.tvSite.text = article.source.name
        binding.tvTimeAgo.text = "${DateTimeUtil.getHoursAgoFromDate(article.publishedAt)}"
        article.source.name.split('.')[0].let {
            it.split(' ')[0].let {
                binding.tvTag.text = it
            }
        }
        Glide.with(itemView)
            .load(article.urlToImage)
            .error(R.drawable.ic_news_placeholder_small)
            .placeholder(R.drawable.ic_news_placeholder_small)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
            .into(binding.ivImage)
    }
}