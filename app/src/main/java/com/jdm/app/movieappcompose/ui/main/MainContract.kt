package com.jdm.app.movieappcompose.ui.main

import com.jdm.app.movieappcompose.base.LoadState
import com.jdm.app.movieappcompose.base.ViewIntent
import com.jdm.app.movieappcompose.base.ViewSideEffect
import com.jdm.app.movieappcompose.base.ViewState

class MainContract {
    data class MainViewState(
        val loadState: LoadState = LoadState.NONE
    ): ViewState

    sealed class MainSideEffect: ViewSideEffect {
        object MoveSearch: MainSideEffect()
        object MoveFavorite: MainSideEffect()
    }
    sealed class MainIntent: ViewIntent {
        object OnClickSearch: MainIntent()
        object OnClickFavorite: MainIntent()
    }
}