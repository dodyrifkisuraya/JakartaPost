package com.dorizu.jakartapost.utils

import com.dorizu.jakartapost.core.data.source.remote.response.NewsItemResponse
import com.dorizu.jakartapost.domain.model.NewsItem

object DataMapper {
    fun mapNewsItemResponseToDomain(input: List<NewsItemResponse>) =
        input.map {
            NewsItem(
                id = it.id,
                title = it.title,
                thumbnail = it.gallery?.first()?.pathOrigin,
                summary = it.summary,
                publishedDate = it.publishedDate
            )
        }
}