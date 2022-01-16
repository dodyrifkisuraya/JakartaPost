package com.dorizu.jakartapost.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorizu.jakartapost.R
import com.dorizu.jakartapost.core.data.ResultState
import com.dorizu.jakartapost.databinding.ActivityMainBinding
import com.dorizu.jakartapost.domain.model.NewsItem
import com.dorizu.jakartapost.presentation.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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

        with(binding.rvNews){
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun showListNews(it: ResultState<List<NewsItem>>?) {
        when(it){
            is ResultState.Success -> {
                newsAdapter.submitList(it.data)
            }
        }
    }
}