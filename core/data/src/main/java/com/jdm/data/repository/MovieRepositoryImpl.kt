package com.jdm.data.repository

import com.jdm.model.Movie
import com.jdm.data.mapper.toMovie
import com.jdm.data.mapper.toMovieEntity
import com.jdm.database.dao.MovieDao
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieRepository{
    override suspend fun insertFavoriteMovie(movie: Movie) {
        val lastRank = movieDao.loadAll().size
        movieDao.insert(movie.copy(rank = lastRank).toMovieEntity())
    }

    override suspend fun updateFavoriteAllMovie(movies: List<Movie>) {
        movieDao.updateAll(movies.map { it.toMovieEntity() })
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        movieDao.delete(movie.toMovieEntity())
        val updatedRankMovieList = getFavoriteAllMovie().mapIndexed { index, movieEntity ->
            movieEntity.copy(rank = index)
        }
        updateFavoriteAllMovie(updatedRankMovieList)
    }

    override suspend fun getFavoriteAllMovie(): List<Movie> {
        return movieDao.loadAll().map { it.toMovie() }
    }
}