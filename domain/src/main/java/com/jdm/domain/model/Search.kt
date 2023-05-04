package com.jdm.domain.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("Search")
    val searches: MutableList<Movie> = mutableListOf(),

    @SerializedName("totalResults")
    val totalCnt: Int = 0
    ) {
    companion object {
        val EMPTY = Search(mutableListOf(), 0)
    }
}