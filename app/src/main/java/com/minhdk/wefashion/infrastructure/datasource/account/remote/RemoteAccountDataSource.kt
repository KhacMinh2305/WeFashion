package com.minhdk.wefashion.infrastructure.datasource.account.remote

import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPasswordValidate
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestLoginAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestRegisterAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordCredential
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordValidation

interface RemoteAccountDataSource {

    suspend fun registerAccount(body: RequestRegisterAccount): ResponseAccount?

    suspend fun loginAccount(body: RequestLoginAccount): ResponseAccount?

    suspend fun forgotPassword(body: RequestForgotPassword): ResponseForgotPasswordCredential?

    suspend fun validateForgotPassword(body: RequestForgotPasswordValidate): ResponseForgotPasswordValidation?

    suspend fun changePassword(body: RequestChangePassword): ResponseChangePassword?
}

