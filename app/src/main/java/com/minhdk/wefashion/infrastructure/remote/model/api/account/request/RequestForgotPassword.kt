package com.minhdk.wefashion.infrastructure.remote.model.api.account.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestForgotPassword(
    @SerializedName("email")
    @Expose
    val email: String
)

