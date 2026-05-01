package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.category.RemoteCategories
import com.minhdk.wefashion.infrastructure.remote.model.api.category.RemoteCategory
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApiService {

    @GET("/api/category")
    suspend fun getCategories(): BaseResponse<RemoteCategories>

    @GET("/api/category")
    suspend fun getCategory(@Query("category_id") id: Int): BaseResponse<RemoteCategory>

}