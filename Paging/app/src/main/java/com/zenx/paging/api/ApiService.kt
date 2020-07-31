package com.zenx.paging.api

import com.zenx.paging.model.Article
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Long
 *
 * Backend APIs
 */
interface ApiService {

    @GET("article/list/{page}/json")
    suspend fun articles(
        @Path("page") page: Int
    ): ApiResponse<ListWrapper<Article>>

}