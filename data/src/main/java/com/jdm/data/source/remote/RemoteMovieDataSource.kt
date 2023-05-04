package com.jdm.data.source.remote

import com.jdm.data.model.SearchResp
import javax.inject.Inject

class RemoteMovieDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getSearchResp(apiKey: String, keyword: String, page: Int): SearchResp {
        return apiService.getSearchResp(apiKey, keyword, page)
    }
}