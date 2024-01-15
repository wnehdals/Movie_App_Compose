package com.hiconsy.favorite

import com.jdm.common.ViewEvent
import com.jdm.common.ViewSideEffect
import com.jdm.common.ViewState
import com.jdm.model.Movie

class FavoriteContract {
    sealed class FavoriteViewState: ViewState {
        object Undefine: FavoriteViewState()
        object Loading: FavoriteViewState()
        data class Success(
            val movies: List<Movie>
        ): FavoriteViewState()
        data class Fail(
            val msg: String
        )
    }
    sealed class FavoriteSideEffect: ViewSideEffect {
        data class ShowDialog(val title: String, val msg: String): FavoriteSideEffect()
    }
    sealed class FavoriteEvent: ViewEvent {
        data class OnClickFavorite(val movie: Movie): FavoriteEvent()
    }
}