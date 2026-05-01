package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPasswordValidate
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestLoginAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestRegisterAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordCredential
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordValidation

interface AccountRepository {

    suspend fun registerAccount(body: RequestRegisterAccount): RequestResult<EntityAccount>

    suspend fun loginAccount(body: RequestLoginAccount): RequestResult<Unit?>

    suspend fun forgotPassword(body: RequestForgotPassword): RequestResult<ResponseForgotPasswordCredential?>

    suspend fun validateForgotPassword(
        body: RequestForgotPasswordValidate
    ): RequestResult<ResponseForgotPasswordValidation?>

    suspend fun changePassword(body: RequestChangePassword): RequestResult<ResponseChangePassword?>
}

