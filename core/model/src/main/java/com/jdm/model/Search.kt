package com.jdm.model

import com.jdm.model.Movie

data class Search(
    val totalCnt: Int = Int.MAX_VALUE,
    val movies: List<Movie> = emptyList(),
    val keyword: String = ""
)
