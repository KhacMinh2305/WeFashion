package com.minhdk.wefashion.infrastructure.remote.model.api.account.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestForgotPasswordValidate(
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("code")
    @Expose
    val code: String,
    @SerializedName("credential")
    @Expose
    val credential: String
)
