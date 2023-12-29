package com.jdm.data.repository

import com.jdm.model.Movie

interface MovieRepository {
    suspend fun insertFavoriteMovie(movie: Movie)

    suspend fun updateFavoriteAllMovie(movies: List<Movie>)

    suspend fun deleteFavoriteMovie(movie: Movie)

    suspend fun getFavoriteAllMovie(): List<Movie>
}