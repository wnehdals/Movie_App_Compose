package com.hiconsy.favorite

import com.jdm.data.model.ModelWrapper
import com.jdm.data.repository.SearchRepository
import com.jdm.model.Search
import com.jdm.data.repository.MovieRepository
import com.jdm.model.Movie
import javax.inject.Inject

class GetFavoriteMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun invoke(): List<Movie> {
        return movieRepository.getFavoriteAllMovie()
    }
}