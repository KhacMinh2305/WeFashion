package com.minhdk.wefashion.infrastructure.remote.model.api.account.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestChangePassword(
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("old_password")
    @Expose
    val oldPassword: String,
    @SerializedName("new_password")
    @Expose
    val newPassword: String
)
