package com.minhdk.wefashion.infrastructure.remote.model.api.cart.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestUpdateCartSku(
    @SerializedName("sku")
    @Expose
    val sku: Int,
    @SerializedName("change")
    @Expose
    val change: String,
    @SerializedName("amount")
    @Expose
    val amount: Int
)

