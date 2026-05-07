package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.search.response.ResponseSearchData
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("/api/search")
    suspend fun search(
        @Query("query") query: String,
        @Query("limit") limit: Int
    ): BaseResponse<ResponseSearchData>
}