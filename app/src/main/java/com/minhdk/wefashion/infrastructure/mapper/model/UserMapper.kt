package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.user.DtoUser
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser
import com.minhdk.wefashion.infrastructure.remote.model.api.user.response.ResponseUser

fun ResponseUser.toEntityUser(): EntityUser? {
    return if (id == null || name == null || avatarUrl == null || email == null || phoneNumber == null || bio == null) {
        null
    } else {
        EntityUser(
            id = id,
            name = name,
            avatarUrl = avatarUrl,
            email = email,
            phoneNumber = phoneNumber,
            bio = bio
        )
    }
}

fun EntityUser.toDtoUser(): DtoUser {
    return DtoUser(
        id = id,
        name = name,
        avatarUrl = avatarUrl,
        email = email,
        phoneNumber = phoneNumber,
        bio = bio
    )
}

