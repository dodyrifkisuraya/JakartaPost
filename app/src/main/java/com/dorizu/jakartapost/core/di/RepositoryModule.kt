package com.dorizu.jakartapost.core.di

import com.dorizu.jakartapost.core.data.repository.NewsRepository
import com.dorizu.jakartapost.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideGithubRepository(newsRepository: NewsRepository): INewsRepository
}