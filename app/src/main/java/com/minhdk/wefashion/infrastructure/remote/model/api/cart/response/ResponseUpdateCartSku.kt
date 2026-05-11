package com.minhdk.wefashion.infrastructure.remote.model.api.cart.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseUpdateCartSku(
    @SerializedName("applied")
    @Expose
    val applied: Int?,
    @SerializedName("expected")
    @Expose
    val expected: Int?
)

