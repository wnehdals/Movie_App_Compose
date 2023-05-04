package com.jdm.data.source.remote

import com.jdm.data.model.SearchResp
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getSearchResp(
        @Query("apikey") apiKey: String,
        @Query("s") keyword: String,
        @Query("page") page: Int
    ): SearchResp

}