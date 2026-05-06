package com.minhdk.wefashion.infrastructure.remote.model.api.product.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseProductColor(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("rgb")
    @Expose
    val rgb: String?
)

