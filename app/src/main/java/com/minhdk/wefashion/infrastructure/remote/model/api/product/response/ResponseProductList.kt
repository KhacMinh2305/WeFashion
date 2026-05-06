package com.minhdk.wefashion.infrastructure.remote.model.api.product.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseProductList(
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("limit")
    @Expose
    val limit: Int?,
    @SerializedName("offset")
    @Expose
    val offset: Int?,
    @SerializedName("data")
    @Expose
    val data: List<ResponseProduct>?
)

