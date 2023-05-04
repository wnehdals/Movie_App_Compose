package com.jdm.domain.repository

import com.jdm.domain.NetworkResult
import com.jdm.domain.model.Movie
import com.jdm.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getSearchResp(keyword: String, page: Int): Flow<Result<Search>>

    fun insertMovie(movie: Movie)

    fun updateAllMovie(movies: List<Movie>)

    fun deleteMovie(movie: Movie)

    //suspend fun loadAllMovie(): Flow<MutableList<Movie>>
}