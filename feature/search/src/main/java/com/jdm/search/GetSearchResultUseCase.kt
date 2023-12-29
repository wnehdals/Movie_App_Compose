package com.jdm.search

import com.jdm.data.model.ModelWrapper
import com.jdm.data.repository.SearchRepository
import com.jdm.model.Search
import com.jdm.data.repository.MovieRepository
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val movieRepository: MovieRepository
) {
    suspend fun invoke(keyword: String, page: Int): ModelWrapper<Search> {
        val result = searchRepository.getSearchResult(keyword, page)
        val favoriteMovies = movieRepository.getFavoriteAllMovie()
        when (result) {
            is ModelWrapper.Success -> {
                val sortedList = result.entity.movies.map {  movie ->
                    var isExist = favoriteMovies.filter { favoriteMovie ->
                        movie.id == favoriteMovie.id
                    }.firstOrNull()
                    if (isExist != null) {
                        movie.isFavorite.value = true
                    }
                    movie
                }
                result.entity.copy(movies = sortedList)
                return ModelWrapper.Success(result.entity)
            }
            is ModelWrapper.Fail -> {
                return result
            }
        }
    }
}