package com.dorizu.jakartapost.domain.usecase

import com.dorizu.jakartapost.domain.repository.INewsRepository
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val newsRepository: INewsRepository
): INewsUseCase {
    override fun getListNews(limit: Int?, skip: Int?) = newsRepository.getListNews(limit, skip)
}