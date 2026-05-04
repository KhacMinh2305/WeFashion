package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.authentication.DtoChangePassword
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseChangePassword


fun ResponseChangePassword.toDtoChangePassword(): DtoChangePassword {
    return DtoChangePassword(
        message = message ?: ""
    )
}