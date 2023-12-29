package com.jdm.data.repository

import com.jdm.model.Search
import com.jdm.data.mapper.SearchMapper
import com.jdm.data.model.ModelWrapper
import com.jdm.database.dao.MovieDao
import com.jdm.network.api.ApiService
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : SearchRepository {
    override suspend fun getSearchResult(keyword: String, page: Int): ModelWrapper<Search> {
        return SearchMapper().mapFromApi(
            remoteRep = apiService.getSearchResp(
                keyword = keyword,
                page = page
            )
        )
    }

}