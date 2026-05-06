package com.minhdk.wefashion.infrastructure.remote.model.api.coupon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteGetCouponByIdResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("shop_id")
    @Expose
    val shopId: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("banner_url")
    @Expose
    val bannerUrl: String?,
    @SerializedName("discount_value")
    @Expose
    val discountValue: Int,
    @SerializedName("discount_type")
    @Expose
    val discountType: String?,
    @SerializedName("amount")
    @Expose
    val amount: Int,
    @SerializedName("created_at")
    @Expose
    val createdAt: String?,
    @SerializedName("expired_at")
    @Expose
    val expiredAt: String,
    @SerializedName("max_discount")
    @Expose
    val maxDiscount: Int,
    @SerializedName("min_order_value")
    @Expose
    val minOrderValue: Int
)

