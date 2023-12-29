package com.jdm.search

import com.jdm.model.Movie
import com.jdm.data.repository.MovieRepository
import javax.inject.Inject

class SaveFavoriteMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun invoke(movie: Movie) {
        movieRepository.insertFavoriteMovie(movie)
    }
}