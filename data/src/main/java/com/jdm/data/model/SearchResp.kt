package com.jdm.data.model

import com.google.gson.annotations.SerializedName
import com.jdm.data.base.BaseResp
import com.jdm.domain.model.Search

data class SearchResp(
    @SerializedName("Search")
    val searches: MutableList<MovieResp>? = mutableListOf(),
    @SerializedName("totalResults")
    val totalCnt: Int = 0,
) : BaseResp()
internal fun SearchResp.toDomain() = Search(
    searches = searches?.map { it.toDomain() }?.toMutableList() ?: mutableListOf(),
    totalCnt = totalCnt,
)