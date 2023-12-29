package com.jdm.search

import androidx.compose.runtime.Immutable
import com.jdm.model.Movie
import com.jdm.common.LoadState
import com.jdm.common.ViewEvent
import com.jdm.common.ViewSideEffect
import com.jdm.common.ViewState

class SearchContract {
    @Immutable
    data class SearchViewState(
        val loadState: LoadState = LoadState.Undefine,
        val totalCnt: Int = Int.MAX_VALUE,
        val movies: List<Movie> = listOf(),
        val keyword: String = "",
    ): ViewState
    sealed class SearchSideEffect: ViewSideEffect {
        data class ShowDialog(val title: String, val msg: String): SearchSideEffect()
    }
    sealed class SearchEvent : ViewEvent {
        data class OnClickSearch(val keyword: String) : SearchEvent()
        object MoreSearch: SearchEvent()
        data class OnClickFavorite(val movie: Movie) : SearchEvent()
    }

}