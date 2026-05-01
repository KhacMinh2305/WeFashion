package com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteCoupon(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("shop_id")
    @Expose
    val shopId: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("discount")
    @Expose
    val discount: Int,
    @SerializedName("expired_at")
    @Expose
    val expiredAt: String
)

