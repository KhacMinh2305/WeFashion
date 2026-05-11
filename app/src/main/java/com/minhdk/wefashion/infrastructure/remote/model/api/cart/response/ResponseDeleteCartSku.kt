package com.minhdk.wefashion.infrastructure.remote.model.api.cart.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDeleteCartSku(
    @SerializedName("success")
    @Expose
    val success: Boolean?,
    @SerializedName("message")
    @Expose
    val message: String?
)

