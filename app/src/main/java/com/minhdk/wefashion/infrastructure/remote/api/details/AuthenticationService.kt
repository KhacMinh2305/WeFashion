package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken
import retrofit2.Call
import retrofit2.http.POST

interface AuthenticationService {

    @POST("api/auth")
    fun fetchToken(): Call<BaseResponse<DataAccessToken>>

}