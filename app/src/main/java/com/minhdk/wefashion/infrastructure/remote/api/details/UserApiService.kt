package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.user.request.RequestUpdateUser
import com.minhdk.wefashion.infrastructure.remote.model.api.user.response.ResponseUser
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

	@GET("/api/user")
	suspend fun getUserById(
		@Query("user_id") userId: Int
	): BaseResponse<ResponseUser>

	@POST("/api/user/{id}/update")
	suspend fun updateUser(
		@Path("id") id: Int,
		@Body body: RequestUpdateUser
	): BaseResponse<ResponseUser>

}

