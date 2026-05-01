package com.minhdk.wefashion.infrastructure.remote.api

import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken
import retrofit2.Call
import retrofit2.http.POST

interface AuthenticationService {

    @POST("api/auth")
    fun fetchToken(): Call<com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken>>

}