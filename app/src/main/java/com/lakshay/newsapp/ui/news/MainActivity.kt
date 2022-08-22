package com.lakshay.newsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jaeger.library.StatusBarUtil
import com.lakshay.newsapp.R
import com.lakshay.newsapp.data.model.RestClientResult
import com.lakshay.newsapp.databinding.ActivityMainBinding
import com.lakshay.newsapp.ui.adapters.NewsAdapter
import com.lakshay.newsapp.ui.viewModels.NewsViewModel
import com.lakshay.newsapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var adapter: NewsAdapter? = null

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor()
        initializeWeather()
        initializeRecyclerView()
        getData()
        observeLiveData()
    }

    private fun setStatusBarColor() {
        StatusBarUtil.setColorNoTranslucent(
            this,
            ContextCompat.getColor(this, android.R.color.black)
        )
    }

    private fun initializeWeather() {
        Glide.with(this)
            .load(Constants.WEATHER_LOGO)
            .error(R.drawable.ic_news_placeholder_small)
            .placeholder(R.drawable.ic_news_placeholder_small)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
            .into(binding.ivWeather)
    }

    private fun initializeRecyclerView() {
        adapter = NewsAdapter { article ->
            startNewsDetailActivity(article.url)
        }
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.adapter = adapter
    }

    private fun getData() {
        mainViewModel.fetchNews(Constants.country, Constants.apikey, Constants.pageSize)
    }

    private fun observeLiveData() {
        mainViewModel.newsLiveData.observe(this) {
            when (it.status) {
                RestClientResult.Status.LOADING -> {
                    binding.rvNews.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                RestClientResult.Status.SUCCESS -> {
                    it.data?.let {
                        binding.progressBar.visibility = View.GONE
                        binding.rvNews.visibility = View.VISIBLE
                        adapter?.submitList(it.articles.toList())
                    }
                }
                RestClientResult.Status.ERROR -> {
                    binding.rvNews.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    Toasty.error(this, "${it.message}", Toasty.LENGTH_SHORT).show()
                }
            }
        }
    }

   private fun startNewsDetailActivity(url: String?){
       startActivity(NewsDetailActivity.newIntent(this, url!!))
   }
}