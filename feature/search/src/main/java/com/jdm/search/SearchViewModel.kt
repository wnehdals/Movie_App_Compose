package com.jdm.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.jdm.model.Movie
import com.jdm.common.BaseViewModel
import com.jdm.common.LoadState
import com.jdm.data.model.ModelWrapper
import com.jdm.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val searchRepository: SearchRepository
) : BaseViewModel<SearchContract.SearchViewState, SearchContract.SearchSideEffect, SearchContract.SearchEvent>(
    SearchContract.SearchViewState()
) {
    val REQUEST_PAGE_RANGE = 10
    var page = 0
    override fun handleEvents(event: SearchContract.SearchEvent) {
        when (event) {
            is SearchContract.SearchEvent.OnClickSearch -> {
                searchKeyword(event.keyword)
            }

            is SearchContract.SearchEvent.MoreSearch -> {
                moreSearch()
            }
            is SearchContract.SearchEvent.OnClickFavorite -> {
                toggleFavorite(event.movie)
            }
        }
    }
    fun toggleFavorite(movie: Movie) {
        if (movie.isFavorite.value) {
            removeFavoriteMovie(movie)
        } else {
            addFavoriteMovie(movie)
        }
    }

    fun removeFavoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteMovieUseCase.invoke(movie)
            withContext(Dispatchers.Main) {
                currentState.movies.forEach {
                    if (it.id == movie.id)
                        it.isFavorite.value = !movie.isFavorite.value
                }
            }
        }
    }
    fun addFavoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            saveFavoriteMovieUseCase.invoke(movie)
            withContext(Dispatchers.Main) {
                currentState.movies.forEach {
                    if (it.id == movie.id)
                        it.isFavorite.value = !movie.isFavorite.value
                }
            }
        }
    }

    fun moreSearch() {
        viewModelScope.launch {
            val maxPage = if (currentState.totalCnt % REQUEST_PAGE_RANGE == 0) {
                currentState.totalCnt / REQUEST_PAGE_RANGE
            } else {
                currentState.totalCnt / REQUEST_PAGE_RANGE + 1
            }
            if (page <= maxPage) {
                page += 1
                val searchResult = getSearchResultUseCase.invoke(currentState.keyword, page)
                when (searchResult) {
                    is ModelWrapper.Success -> {
                        val newMovieList = mutableListOf<Movie>()
                        newMovieList.addAll(currentState.movies)
                        newMovieList.addAll(searchResult.entity.movies)
                        updateState {
                            copy(
                                movies = newMovieList,
                                totalCnt = searchResult.entity.totalCnt,
                            )
                        }
                    }

                    is ModelWrapper.Fail -> {
                        sendEffect({
                            SearchContract.SearchSideEffect.ShowDialog("오류", searchResult.error)
                        })
                    }
                }
            }
        }
    }

    fun searchKeyword(keyword: String) {
        viewModelScope.launch {
            if (keyword.isEmpty())
                return@launch

            val maxPage = if (currentState.totalCnt % REQUEST_PAGE_RANGE == 0) {
                currentState.totalCnt / REQUEST_PAGE_RANGE
            } else {
                currentState.totalCnt / REQUEST_PAGE_RANGE + 1
            }
            if (currentState.keyword != keyword) {
                page = 1
            }
            updateState { copy(
                loadState = LoadState.Loading
            ) }
            val searchResult = getSearchResultUseCase.invoke(keyword, page)
            when (searchResult) {
                is ModelWrapper.Success -> {
                    updateState {
                        copy(
                            loadState = LoadState.Success,
                            movies = searchResult.entity.movies,
                            totalCnt = searchResult.entity.totalCnt,
                            keyword = keyword,
                        )
                    }
                }

                is ModelWrapper.Fail -> {
                    updateState {
                        copy(
                            loadState = LoadState.Fail
                        )
                    }
                    sendEffect({
                        SearchContract.SearchSideEffect.ShowDialog("오류", searchResult.error)
                    })
                }
            }
        }
    }
}