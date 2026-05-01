package com.minhdk.wefashion.infrastructure.datasource.account.remote

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPasswordValidate
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestLoginAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestRegisterAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordCredential
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordValidation
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class RemoteAccountDataSourceImpl @Inject constructor(
    private val apiService: DataApiService
) : RemoteAccountDataSource {

    override suspend fun registerAccount(
        body: RequestRegisterAccount
    ): ResponseAccount? = withContext(Dispatchers.IO) {
        apiService.registerAccount(body).data
    }

    override suspend fun loginAccount(
        body: RequestLoginAccount
    ): ResponseAccount? = withContext(Dispatchers.IO) {
        apiService.loginAccount(body).data
    }

    override suspend fun forgotPassword(
        body: RequestForgotPassword
    ): ResponseForgotPasswordCredential? = withContext(Dispatchers.IO) {
        apiService.forgotPassword(body).data
    }

    override suspend fun validateForgotPassword(
        body: RequestForgotPasswordValidate
    ): ResponseForgotPasswordValidation? = withContext(Dispatchers.IO) {
        apiService.validateForgotPassword(body).data
    }

    override suspend fun changePassword(
        body: RequestChangePassword
    ): ResponseChangePassword? = withContext(Dispatchers.IO) {
        apiService.changePassword(body).data
    }
}

