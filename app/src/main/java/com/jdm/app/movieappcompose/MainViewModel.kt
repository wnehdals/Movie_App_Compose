package com.jdm.app.movieappcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdm.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
): ViewModel() {
    fun getSearchResult() {
        viewModelScope.launch {
            getSearchUseCase.invoke("king",1)
                .collectLatest {
                    it.onSuccess {
                        Log.e("jdm_tag",it.toString())
                    }.onFailure {
                        Log.e("jdm_tag", it.toString())
                    }
                }
        }
    }
}