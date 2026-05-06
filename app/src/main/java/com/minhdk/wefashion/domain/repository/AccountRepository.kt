package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.authentication.DtoAccount
import com.minhdk.wefashion.domain.data.authentication.DtoChangePassword
import com.minhdk.wefashion.domain.data.authentication.DtoForgotPasswordCredential
import com.minhdk.wefashion.domain.data.authentication.DtoForgotPasswordValidation

interface AccountRepository {

    fun getCurrentAccount(): DtoAccount?

    suspend fun registerAccount(
        email: String,
        username: String,
        password: String
    ): RequestResult<DtoAccount>

    suspend fun loginAccount(
        username: String,
        password: String
    ): RequestResult<Unit?>

    suspend fun forgotPassword(email: String): RequestResult<DtoForgotPasswordCredential?>

    suspend fun validateForgotPassword(
        email: String,
        code: String,
        credential: String
    ): RequestResult<DtoForgotPasswordValidation?>

    suspend fun changePassword(
        username: String,
        oldPassword: String,
        newPassword: String
    ): RequestResult<DtoChangePassword>
}
