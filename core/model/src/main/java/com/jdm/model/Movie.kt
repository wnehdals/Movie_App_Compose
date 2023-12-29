package com.jdm.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Movie(
    val id: String = "",
    val title: String = "",
    val year: String = "",
    val poster: String = "",
    val rank: Int = -1,
    val isFavorite: MutableState<Boolean> = mutableStateOf(false)
)
