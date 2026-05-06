package com.minhdk.wefashion.infrastructure.remote.model.api.product.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseProductsByShop(
    @SerializedName("shop")
    @Expose
    val shop: ResponseProductShop?,
    @SerializedName("products")
    @Expose
    val products: List<ResponseProduct>?
)

