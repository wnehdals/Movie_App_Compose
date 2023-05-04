package com.jdm.data.di

import com.jdm.data.source.local.LocalMovieDataSource
import com.jdm.data.source.local.dao.MovieDao
import com.jdm.data.source.remote.ApiService
import com.jdm.data.source.remote.RemoteMovieDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteMovieDataSource(service: ApiService): RemoteMovieDataSource {
        return RemoteMovieDataSource(service)
    }
    @Provides
    @Singleton
    fun provideLocalMovieDataSource(movieDao: MovieDao): LocalMovieDataSource {
        return LocalMovieDataSource(movieDao)
    }
}