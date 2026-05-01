package com.minhdk.wefashion.infrastructure.remote.model.api.account.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseChangePassword(
    @SerializedName("message")
    @Expose
    val message: String?
)
