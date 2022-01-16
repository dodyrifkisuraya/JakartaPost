package com.dorizu.jakartapost.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.dorizu.jakartapost.core.data.source.remote.network.ApiResponse
import com.dorizu.jakartapost.core.data.source.remote.network.ApiService
import com.dorizu.jakartapost.core.data.source.remote.response.NewsItemResponse
import com.dorizu.jakartapost.core.data.source.remote.response.NewsResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    @SuppressLint("CheckResult")
    fun getListNews(limit: Int? = null, skip: Int? = null): Flowable<ApiResponse<List<NewsItemResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<NewsItemResponse>>>()

        apiService.getListNews(limit, skip)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({response ->
                val data = response.data
                resultData.onNext(if (!data.isNullOrEmpty()) ApiResponse.Success(data) else ApiResponse.Empty)
            },{ error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e(TAG, error.message.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    companion object{
        const val TAG = "RemoteDataSource"
    }
}