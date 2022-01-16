package com.dorizu.jakartapost.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorizu.jakartapost.core.data.ResultState
import com.dorizu.jakartapost.domain.model.NewsItem
import com.dorizu.jakartapost.domain.usecase.INewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsUseCase: INewsUseCase
): ViewModel() {

    private val _listNews = MutableLiveData<ResultState<List<NewsItem>>>()
    val listNews: LiveData<ResultState<List<NewsItem>>> get() = _listNews

    init {
        getListNews()
    }

    fun getListNews(limit: Int? = null, skip: Int? = null){
        val disposable = newsUseCase.getListNews(limit, skip)
            .subscribe{res ->
                _listNews.value = res
            }
        mCompositeDisposable.add(disposable)
    }

    companion object{
        private val mCompositeDisposable = CompositeDisposable()
    }
}