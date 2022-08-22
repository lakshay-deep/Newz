package com.lakshay.newsapp.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.lakshay.newsapp.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity(){

    private lateinit var url: String

    private lateinit var binding: ActivityNewsDetailBinding

    companion object {
        private const val EXTRA_URL = "EXTRA_URL"
        fun newIntent(context: Context, url: String): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setStatusBarColor()
        getArguments()
        initWebView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    private fun setStatusBarColor() {
        StatusBarUtil.setColorNoTranslucent(
            this,
            ContextCompat.getColor(this, android.R.color.black)
        )
    }

    private fun getArguments() {
        intent?.let {
            it.getStringExtra(EXTRA_URL)?.let {
                url = it
            }
        }
    }

    private fun initWebView() {
        binding.webView.settings?.let {
            it.javaScriptEnabled = true
            it.loadWithOverviewMode = true
            it.useWideViewPort = true
            it.builtInZoomControls = true
            it.displayZoomControls = false
        }
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }
        }
        binding.webView.loadUrl(url)
    }
}