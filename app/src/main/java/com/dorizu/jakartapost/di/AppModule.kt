package com.dorizu.jakartapost.di

import com.dorizu.jakartapost.domain.usecase.INewsUseCase
import com.dorizu.jakartapost.domain.usecase.NewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideGithubRepositoryUseCase(newsUseCase: NewsUseCase): INewsUseCase
}