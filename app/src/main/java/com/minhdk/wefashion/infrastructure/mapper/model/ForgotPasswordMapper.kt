package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.authentication.DtoForgotPasswordCredential
import com.minhdk.wefashion.domain.data.authentication.DtoForgotPasswordValidation
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordCredential
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseForgotPasswordValidation

fun ResponseForgotPasswordCredential.toDtoForgotPasswordCredential(): DtoForgotPasswordCredential {
    return DtoForgotPasswordCredential(
        credential = credential
    )
}

fun ResponseForgotPasswordValidation.toDtoForgotPasswordValidation(): DtoForgotPasswordValidation {
    return DtoForgotPasswordValidation(
        isValid = isValid,
        detail = detail
    )
}

