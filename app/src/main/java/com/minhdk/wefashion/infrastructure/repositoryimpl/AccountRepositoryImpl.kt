package com.minhdk.wefashion.infrastructure.repositoryimpl

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.AccountRepository
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityAccount
import com.minhdk.wefashion.infrastructure.datasource.account.local.LocalAccountDataSource
import com.minhdk.wefashion.infrastructure.datasource.account.remote.RemoteAccountDataSource
import com.minhdk.wefashion.infrastructure.mapper.toEntityAccount
import com.minhdk.wefashion.infrastructure.mapper.toEntityUser
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPasswordValidate
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestLoginAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestRegisterAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordCredential
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordValidation
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteAccountDataSource,
    private val localSource: LocalAccountDataSource
) : AccountRepository {

    private suspend fun cacheAccountAndUser(account: ResponseAccount?) : EntityAccount {
        return account?.let {
            val acc = account.toEntityAccount() ?: throw Exception("Important field is null")
            val user = account.toEntityUser() ?: throw Exception("Important field is null")
            localSource.insertAccount(acc)
            localSource.insertUser(user)
            acc
        } ?: run {
            throw Exception("Account is null")
        }
    }

    override suspend fun registerAccount(
        body: RequestRegisterAccount
    ): RequestResult<EntityAccount> {
        return try {
            val entity = cacheAccountAndUser(remoteSource.registerAccount(body))
            return RequestResult.Success(entity)
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun loginAccount(
        body: RequestLoginAccount
    ): RequestResult<Unit?> {
        return try {
            cacheAccountAndUser(remoteSource.loginAccount(body))
            RequestResult.Success(Unit)
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun forgotPassword(
        body: RequestForgotPassword
    ): RequestResult<ResponseForgotPasswordCredential?> {
        return try {
            RequestResult.Success(remoteSource.forgotPassword(body))
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun validateForgotPassword(
        body: RequestForgotPasswordValidate
    ): RequestResult<ResponseForgotPasswordValidation?> {
        return try {
            RequestResult.Success(remoteSource.validateForgotPassword(body))
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun changePassword(
        body: RequestChangePassword
    ): RequestResult<ResponseChangePassword?> {
        return try {
            RequestResult.Success(remoteSource.changePassword(body))
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }
}

