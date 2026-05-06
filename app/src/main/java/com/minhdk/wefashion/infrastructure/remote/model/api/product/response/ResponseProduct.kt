package com.minhdk.wefashion.infrastructure.remote.model.api.product.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseProduct(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("image_url")
    @Expose
    val imageUrl: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("rating")
    @Expose
    val rating: Double?,
    @SerializedName("sold_amount")
    @Expose
    val soldAmount: Int?,
    @SerializedName("liked_amount")
    @Expose
    val likedAmount: Int?,
    @SerializedName("category_id")
    @Expose
    val categoryId: Int?,
    @SerializedName("shop_id")
    @Expose
    val shopId: Int?
)

