package com.jdm.app.movieappcompose.ui.search

import com.jdm.app.movieappcompose.base.LoadState
import com.jdm.app.movieappcompose.base.ViewIntent
import com.jdm.app.movieappcompose.base.ViewSideEffect
import com.jdm.app.movieappcompose.base.ViewState
import com.jdm.domain.model.Movie

class SearchContract {
    data class SearchViewState(
        val loadState: LoadState = LoadState.NONE,
        val keyword: String = "",
        val movieList: MutableList<Movie> = mutableListOf<Movie>()

    ): ViewState

    sealed class SearchSideEffect: ViewSideEffect {
    }
    sealed class SearchIntent: ViewIntent {
        object ClickSearchEvent: SearchIntent()
    }
}