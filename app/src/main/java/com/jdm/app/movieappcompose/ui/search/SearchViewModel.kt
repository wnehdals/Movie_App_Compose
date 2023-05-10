package com.jdm.app.movieappcompose.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdm.app.movieappcompose.base.BaseViewModel
import com.jdm.app.movieappcompose.base.LoadState
import com.jdm.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
): BaseViewModel<SearchContract.SearchViewState, SearchContract.SearchSideEffect, SearchContract.SearchIntent>(
    SearchContract.SearchViewState()
) {
    fun setKeyword(_keyword: String) {
        updateState { copy(keyword = _keyword) }
    }
    fun getSearchResult() {

        viewModelScope.launch {
            getSearchUseCase.invoke(viewState.value.keyword,1)
                .collectLatest {
                    it.onSuccess {
                        updateState { copy(
                            loadState = LoadState.SUCCESS,
                            movieList = it.searches
                        ) }

                    }.onFailure {
                        updateState {
                            copy(
                                loadState = LoadState.FAIL
                            )
                        }
                    }
                }
        }
    }

    override fun handleEvents(event: SearchContract.SearchIntent) {
        when(event) {
            is SearchContract.SearchIntent.ClickSearchEvent -> {
                updateState { copy(loadState = LoadState.LOADING) }
                getSearchResult()
            }
        }
    }
}