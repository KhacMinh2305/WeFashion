package com.minhdk.wefashion.infrastructure.remote

import com.minhdk.wefashion.infrastructure.model.baseResponse.BaseResponse
import com.minhdk.wefashion.infrastructure.model.category.RemoteCategories
import com.minhdk.wefashion.infrastructure.model.category.RemoteCategory
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApiService {

    //----------------------------------------------------------------------
    // Category
    //----------------------------------------------------------------------
    @GET("/api/category")
    suspend fun getCategories(): BaseResponse<RemoteCategories>

    @GET("/api/category")
    suspend fun getCategory(@Query("category_id") id: Int): BaseResponse<RemoteCategory>

}