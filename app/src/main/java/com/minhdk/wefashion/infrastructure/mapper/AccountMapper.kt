package com.minhdk.wefashion.infrastructure.mapper

import com.minhdk.wefashion.infrastructure.database.room.entity.EntityAccount
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.ResponseAccount

fun ResponseAccount.toEntityAccount(): EntityAccount? {
    return if(id == null || name == null || phoneNumber == null) null else {
        EntityAccount(
            id = id,
            username = name,
            phoneNumber = phoneNumber
        )
    }
}

fun ResponseAccount.toEntityUser(): EntityUser? {
    return if(id == null || email == null) null else {
        EntityUser(
            id = id,
            name = name ?: "",
            avatarUrl = avatarUrl ?: "",
            email = email,
            phoneNumber = phoneNumber ?: "",
            bio = bio ?: ""
        )
    }
}

