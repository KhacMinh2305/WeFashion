package com.minhdk.wefashion.infrastructure.remote.model.api.search.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProduct

data class ResponseSearchData(
    @SerializedName("products")
    @Expose
    val products: List<ResponseSearchProduct>?,
    @SerializedName("shops")
    @Expose
    val shops: List<ResponseSearchShop>?
)

