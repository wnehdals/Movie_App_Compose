package com.hiconsy.favorite

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.jdm.common.BaseViewModel
import com.jdm.data.repository.MovieRepository
import com.jdm.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val movieRepository: MovieRepository,
) : BaseViewModel<FavoriteContract.FavoriteViewState, FavoriteContract.FavoriteSideEffect, FavoriteContract.FavoriteEvent>(
    FavoriteContract.FavoriteViewState.Undefine
){
    override fun handleEvents(event: FavoriteContract.FavoriteEvent) {
        when (event) {
            is FavoriteContract.FavoriteEvent.OnClickFavorite -> {
                Log.e("viewmodel", "onFavoriteClickEvent")
                removeFavoriteMovie(event.movie)
            }
        }
    }
    fun getFavoriteMoviews() {
        updateState {
            FavoriteContract.FavoriteViewState.Loading
        }
        viewModelScope.launch(Dispatchers.IO) {

            val result = getFavoriteMovieUseCase.invoke()
            result.forEach { it.isFavorite.value = true }
            with(Dispatchers.Main) {
                updateState {
                    FavoriteContract.FavoriteViewState.Success(movies = result)
                }
            }
        }
    }
    fun removeFavoriteMovie(movie: Movie) {
        updateState {
            FavoriteContract.FavoriteViewState.Loading
        }
        viewModelScope.launch(Dispatchers.IO) {

            deleteFavoriteMovieUseCase.invoke(movie)
            val result = getFavoriteMovieUseCase.invoke()
            with(Dispatchers.Main) {
                updateState {
                    FavoriteContract.FavoriteViewState.Success(movies = result)
                }
            }
        }
    }

}