package com.dorizu.jakartapost.core.data.repository

import com.dorizu.jakartapost.core.data.MapApiResponseToResultState
import com.dorizu.jakartapost.core.data.ResultState
import com.dorizu.jakartapost.core.data.source.remote.RemoteDataSource
import com.dorizu.jakartapost.core.data.source.remote.network.ApiResponse
import com.dorizu.jakartapost.core.data.source.remote.response.NewsItemResponse
import com.dorizu.jakartapost.domain.model.NewsItem
import com.dorizu.jakartapost.domain.repository.INewsRepository
import com.dorizu.jakartapost.utils.DataMapper
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository@Inject constructor(
    private val remoteDataSource: RemoteDataSource
): INewsRepository {
    override fun getListNews(
        limit: Int?,
        skip: Int?
    ): Flowable<ResultState<List<NewsItem>>> =
        object: MapApiResponseToResultState<List<NewsItem>, List<NewsItemResponse>>(){
            override fun createCall(): Flowable<ApiResponse<List<NewsItemResponse>>> {
                return remoteDataSource.getListNews(limit, skip)
            }

            override fun mapResponseToDomain(data: List<NewsItemResponse>): List<NewsItem> {
                return DataMapper.mapNewsItemResponseToDomain(data)
            }

        }.asFlowable()
}