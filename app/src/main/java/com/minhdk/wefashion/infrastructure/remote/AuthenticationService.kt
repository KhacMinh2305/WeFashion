package com.minhdk.wefashion.infrastructure.remote

import com.minhdk.wefashion.infrastructure.model.baseResponse.BaseResponse
import com.minhdk.wefashion.infrastructure.model.token.DataAccessToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationService {

    @POST("api/auth")
    fun fetchToken(): Call<BaseResponse<DataAccessToken>>

}