package com.jdm.network.model

import com.google.gson.annotations.SerializedName

data class SearchResp(
    @SerializedName("Search")
    val searches: MutableList<MovieResp>? = mutableListOf(),
    @SerializedName("totalResults")
    val totalCnt: Int? = 0,
) : BaseResp()

