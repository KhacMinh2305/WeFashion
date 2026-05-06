package com.minhdk.wefashion.infrastructure.remote.model.api.product.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseProductSku(
    @SerializedName("sku")
    @Expose
    val sku: Int?,
    @SerializedName("amount")
    @Expose
    val amount: Int?,
    @SerializedName("price")
    @Expose
    val price: Int?,
    @SerializedName("size")
    @Expose
    val size: ResponseProductSize?,
    @SerializedName("color")
    @Expose
    val color: ResponseProductColor?
)

