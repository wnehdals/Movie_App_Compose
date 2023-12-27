package com.hiconsy.data.repository

import com.jdm.database.dao.MovieDao
import com.jdm.database.entity.MovieEntity
import com.jdm.movieapp.BuildConfig
import com.jdm.network.api.ApiService
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : SearchRepository {
    override suspend fun getSearchResult(keyword: String, page: Int) {
        apiService.getSearchResp(
            apiKey = BuildConfig.API_KEY,
            keyword = keyword,
            page = page)
    }
}