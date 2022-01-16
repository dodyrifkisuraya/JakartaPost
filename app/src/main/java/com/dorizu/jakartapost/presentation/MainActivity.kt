package com.dorizu.jakartapost.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dorizu.jakartapost.R
import com.dorizu.jakartapost.core.data.ResultState
import com.dorizu.jakartapost.databinding.ActivityMainBinding
import com.dorizu.jakartapost.domain.model.NewsItem
import com.dorizu.jakartapost.presentation.DetailNewsActivity.Companion.NEWS_DATA
import com.dorizu.jakartapost.presentation.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.listNews.observe(this){
            showListNews(it)
        }
        binding.swipeHome.setOnRefreshListener(this)

        newsAdapter.onItemClick = { news ->
            val intent = Intent(this, DetailNewsActivity::class.java)
            intent.putExtra(NEWS_DATA, news)
            startActivity(intent)
        }
        with(binding.rvNews){
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun showListNews(it: ResultState<List<NewsItem>>?) {
        binding.apply {
            when(it){
                is ResultState.Success -> {
                    swipeHome.isRefreshing = false
                    newsAdapter.submitList(it.data)
                }
                is ResultState.Loading -> {
                    swipeHome.isRefreshing = true
                }
                else -> swipeHome.isRefreshing = false
            }
        }
    }

    override fun onRefresh() {
        mainViewModel.getListNews()
    }
}