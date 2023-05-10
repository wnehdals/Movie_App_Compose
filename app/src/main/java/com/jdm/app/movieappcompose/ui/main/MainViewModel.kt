package com.jdm.app.movieappcompose.ui.main

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdm.app.movieappcompose.base.BaseViewModel
import com.jdm.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
): BaseViewModel<MainContract.MainViewState, MainContract.MainSideEffect, MainContract.MainIntent>(
    MainContract.MainViewState()
) {
    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    override fun handleEvents(event: MainContract.MainIntent) {
        when (event) {
            is MainContract.MainIntent.OnClickSearch -> {

            }
            is MainContract.MainIntent.OnClickFavorite -> {

            }
        }
    }


    fun selectTab(@StringRes tab: Int) {
        _selectedTab.value = tab
    }
}