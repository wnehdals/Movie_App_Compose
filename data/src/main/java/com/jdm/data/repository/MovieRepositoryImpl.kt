package com.jdm.data.repository

import com.jdm.data.base.apiCall
import com.jdm.data.const.Const.API_KEY
import com.jdm.data.model.toDomain
import com.jdm.data.source.local.LocalMovieDataSource
import com.jdm.data.source.remote.RemoteMovieDataSource
import com.jdm.domain.NetworkResult
import com.jdm.domain.model.Movie
import com.jdm.domain.model.Search
import com.jdm.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) : MovieRepository {
    override suspend fun getSearchResp(
        keyword: String,
        page: Int
    ): Flow<Result<Search>> {
        return apiCall(
            call = {
                remoteMovieDataSource.getSearchResp(API_KEY, keyword, page)
            },
            mapper = {
                it.toDomain()
            }
        )
    }




    override fun insertMovie(movie: Movie) {
    }

    override fun updateAllMovie(movies: List<Movie>) {
    }

    override fun deleteMovie(movie: Movie) {
    }

}