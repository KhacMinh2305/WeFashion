package com.minhdk.wefashion.infrastructure.mapper.helper.user

import com.minhdk.wefashion.infrastructure.remote.model.api.user.request.RequestUpdateUser

fun buildUpdateUserRequest(
    id: Int,
    name: String,
    avatarUrl: String,
    email: String,
    phoneNumber: String,
    bio: String
): RequestUpdateUser {
    return RequestUpdateUser(
        id = id,
        name = name,
        avatarUrl = avatarUrl,
        email = email,
        phoneNumber = phoneNumber,
        bio = bio
    )
}

