package com.dorizu.jakartapost.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dorizu.jakartapost.R
import com.dorizu.jakartapost.databinding.ActivityDetailNewsBinding
import com.dorizu.jakartapost.domain.model.NewsItem

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.getParcelableExtra<NewsItem>(NEWS_DATA)?.let {
            showDetailNews(it)
        }
    }

    private fun showDetailNews(it: NewsItem) {
        binding.apply {
            Glide.with(baseContext)
                .load(it.thumbnail)
                .into(thumbnail)

            tvTitle.text = it.title
            tvDateUpload.text = it.publishedDate
            tvDescription.text = it.summary
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val NEWS_DATA = "news"
    }
}