package com.jdm.data.repository

import com.jdm.data.model.ModelWrapper
import com.jdm.model.Search

interface SearchRepository {
    suspend fun getSearchResult(keyword: String, page: Int): ModelWrapper<Search>
}