package com.minhdk.wefashion.infrastructure.remote.model.api.account.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseForgotPasswordCredential(
    @SerializedName("credential")
    @Expose
    val credential: String?
)
