package com.dorizu.jakartapost.domain.usecase

import com.dorizu.jakartapost.core.data.ResultState
import com.dorizu.jakartapost.domain.model.NewsItem
import io.reactivex.Flowable

interface INewsUseCase {
    fun getListNews(limit: Int? = null, skip: Int? = null): Flowable<ResultState<List<NewsItem>>>
}