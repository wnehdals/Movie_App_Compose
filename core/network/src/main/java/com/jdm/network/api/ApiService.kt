package com.jdm.network.api

import com.jdm.network.model.SearchResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getSearchResp(
        @Query("apikey") apiKey: String = "a4db7b33",
        @Query("s") keyword: String,
        @Query("page") page: Int
    ): Response<SearchResp>
}