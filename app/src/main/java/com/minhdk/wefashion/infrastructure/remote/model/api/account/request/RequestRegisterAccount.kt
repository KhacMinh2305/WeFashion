package com.minhdk.wefashion.infrastructure.remote.model.api.account.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestRegisterAccount(
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("password")
    @Expose
    val password: String
)

