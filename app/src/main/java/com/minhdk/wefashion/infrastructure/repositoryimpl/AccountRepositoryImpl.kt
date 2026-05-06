package com.minhdk.wefashion.infrastructure.repositoryimpl

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.authentication.DtoAccount
import com.minhdk.wefashion.domain.data.authentication.DtoChangePassword
import com.minhdk.wefashion.domain.data.authentication.DtoForgotPasswordCredential
import com.minhdk.wefashion.domain.data.authentication.DtoForgotPasswordValidation
import com.minhdk.wefashion.domain.repository.AccountRepository
import com.minhdk.wefashion.domain.repository.UserRepository
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityAccount
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser
import com.minhdk.wefashion.infrastructure.datasource.account.local.LocalAccountDataSource
import com.minhdk.wefashion.infrastructure.datasource.account.remote.RemoteAccountDataSource
import com.minhdk.wefashion.infrastructure.mapper.helper.authentication.buildChangePasswordRequest
import com.minhdk.wefashion.infrastructure.mapper.helper.authentication.buildForgotPasswordRequest
import com.minhdk.wefashion.infrastructure.mapper.helper.authentication.buildForgotPasswordValidateRequest
import com.minhdk.wefashion.infrastructure.mapper.helper.authentication.buildLoginAccountRequest
import com.minhdk.wefashion.infrastructure.mapper.helper.authentication.buildRegisterAccountRequest
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoAccount
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoChangePassword
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoForgotPasswordCredential
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoForgotPasswordValidation
import com.minhdk.wefashion.infrastructure.mapper.model.toEntityAccount
import com.minhdk.wefashion.infrastructure.mapper.model.toEntityUser
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseAccount
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteAccountDataSource,
    private val localSource: LocalAccountDataSource,
    private val userRepo: UserRepository
) : AccountRepository {

    private var accountThisSession: EntityAccount? = null

    override fun getCurrentAccount(): DtoAccount? = accountThisSession?.toDtoAccount()

    private suspend fun cacheAccountAndUser(account: ResponseAccount?) : EntityAccount {
        return account?.let {
            val acc = account.toEntityAccount() ?: throw Exception("Important field is null")
            val user = account.toEntityUser() ?: throw Exception("Important field is null")
            localSource.insertAccount(acc)
            userRepo.cacheUser(user)
            accountThisSession = acc
            acc
        } ?: run {
            throw Exception("Account is null")
        }
    }

    override suspend fun registerAccount(
        email: String,
        username: String,
        password: String
    ): RequestResult<DtoAccount> {
        return try {
            val body = buildRegisterAccountRequest(email, username, password)
            val entity = cacheAccountAndUser(remoteSource.registerAccount(body))
            RequestResult.Success(entity.toDtoAccount())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun loginAccount(
        username: String,
        password: String
    ): RequestResult<Unit?> {
        return try {
            val body = buildLoginAccountRequest(username, password)
            cacheAccountAndUser(remoteSource.loginAccount(body))
            RequestResult.Success(Unit)
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun forgotPassword(
        email: String
    ): RequestResult<DtoForgotPasswordCredential?> {
        return try {
            val body = buildForgotPasswordRequest(email)
            val res = remoteSource.forgotPassword(body)
            RequestResult.Success(res?.toDtoForgotPasswordCredential())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun validateForgotPassword(
        email: String,
        code: String,
        credential: String
    ): RequestResult<DtoForgotPasswordValidation?> {
        return try {
            val body = buildForgotPasswordValidateRequest(email, code, credential)
            val res = remoteSource.validateForgotPassword(body)
            RequestResult.Success(res?.toDtoForgotPasswordValidation())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun changePassword(
        username: String,
        oldPassword: String,
        newPassword: String
    ): RequestResult<DtoChangePassword> {
        return try {
            val body = buildChangePasswordRequest(username, oldPassword, newPassword)
            val res = remoteSource.changePassword(body) ?: throw Exception("Response is null")
            RequestResult.Success(res.toDtoChangePassword())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }
}
