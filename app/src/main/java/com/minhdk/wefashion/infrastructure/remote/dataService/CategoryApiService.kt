package com.minhdk.wefashion.infrastructure.remote.dataService

import com.minhdk.wefashion.infrastructure.model.api.category.RemoteCategories
import com.minhdk.wefashion.infrastructure.model.api.category.RemoteCategory
import com.minhdk.wefashion.infrastructure.model.baseResponse.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApiService {

    @GET("/api/category")
    suspend fun getCategories(): BaseResponse<RemoteCategories>

    @GET("/api/category")
    suspend fun getCategory(@Query("category_id") id: Int): BaseResponse<RemoteCategory>

}