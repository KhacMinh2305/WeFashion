package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPasswordValidate
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestLoginAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestRegisterAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordCredential
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordValidation
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApiService {

    @POST("/api/account/register")
    suspend fun registerAccount(
        @Body body: RequestRegisterAccount
    ): BaseResponse<ResponseAccount>

    @POST("/api/account/login")
    suspend fun loginAccount(
        @Body body: RequestLoginAccount
    ): BaseResponse<ResponseAccount>

    @POST("/api/account/forgot-password")
    suspend fun forgotPassword(
        @Body body: RequestForgotPassword
    ): BaseResponse<ResponseForgotPasswordCredential>

    @POST("/api/account/forgot-password/validate")
    suspend fun validateForgotPassword(
        @Body body: RequestForgotPasswordValidate
    ): BaseResponse<ResponseForgotPasswordValidation>

    @POST("/api/account/change-password")
    suspend fun changePassword(
        @Body body: RequestChangePassword
    ): BaseResponse<ResponseChangePassword>
}