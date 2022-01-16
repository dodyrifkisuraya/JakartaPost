package com.dorizu.jakartapost.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItem(
    val id: String? = null,
    val title: String? = null,
    val publishedDate: String? = null,
    val thumbnail: String? = null,
    val summary: String? = null
) : Parcelable