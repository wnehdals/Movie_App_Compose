package com.hiconsy.data.repository

import com.hiconsy.data.model.ModelWrapper
import com.jdm.network.model.SearchResp

interface SearchRepository {
    suspend fun getSearchResult(keyword: String, page: Int): ModelWrapper<SearchResp>
}