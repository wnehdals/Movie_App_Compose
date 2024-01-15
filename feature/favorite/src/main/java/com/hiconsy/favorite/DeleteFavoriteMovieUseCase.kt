package com.hiconsy.favorite

import com.jdm.model.Movie
import com.jdm.data.repository.MovieRepository
import javax.inject.Inject

class DeleteFavoriteMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun invoke(movie: Movie) {
        movieRepository.deleteFavoriteMovie(movie)
    }
}