package com.jdm.data.di

import com.jdm.data.repository.MovieRepositoryImpl
import com.jdm.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepository: MovieRepositoryImpl
    ): MovieRepository
}