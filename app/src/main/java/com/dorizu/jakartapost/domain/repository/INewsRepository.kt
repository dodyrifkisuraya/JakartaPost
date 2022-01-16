package com.dorizu.jakartapost.domain.repository

import com.dorizu.jakartapost.core.data.ResultState
import com.dorizu.jakartapost.domain.model.NewsItem
import io.reactivex.Flowable

interface INewsRepository {
    fun getListNews(limit: Int? = null, skip: Int? = null): Flowable<ResultState<List<NewsItem>>>
}