package com.jdm.network.model

import com.google.gson.annotations.SerializedName

data class SearchResp(
    @SerializedName("Search")
    val searches: List<MovieResp>? = listOf(),
    @SerializedName("totalResults")
    val totalCnt: Int? = 0,
) : BaseResp()

