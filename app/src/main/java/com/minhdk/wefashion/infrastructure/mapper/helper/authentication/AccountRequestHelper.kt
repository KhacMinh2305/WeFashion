package com.minhdk.wefashion.infrastructure.mapper.helper.authentication

import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestForgotPasswordValidate
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestLoginAccount
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestRegisterAccount

fun buildRegisterAccountRequest(
    email: String,
    username: String,
    password: String
): RequestRegisterAccount {
    return RequestRegisterAccount(
        email = email,
        username = username,
        password = password
    )
}

fun buildLoginAccountRequest(
    username: String,
    password: String
): RequestLoginAccount {
    return RequestLoginAccount(
        username = username,
        password = password
    )
}

fun buildForgotPasswordRequest(email: String): RequestForgotPassword {
    return RequestForgotPassword(email = email)
}

fun buildForgotPasswordValidateRequest(
    email: String,
    code: String,
    credential: String
): RequestForgotPasswordValidate {
    return RequestForgotPasswordValidate(
        email = email,
        code = code,
        credential = credential
    )
}

fun buildChangePasswordRequest(
    username: String,
    oldPassword: String,
    newPassword: String
): RequestChangePassword {
    return RequestChangePassword(
        username = username,
        oldPassword = oldPassword,
        newPassword = newPassword
    )
}

