package com.dorizu.jakartapost.core.data.source.remote.network

import com.dorizu.jakartapost.core.data.source.remote.response.NewsResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("articles/seasia")
    fun getListNews(
        @Query("limit") limit: Int? = null,
        @Query("skip") skip: Int? = null
    ): Flowable<NewsResponse>
}