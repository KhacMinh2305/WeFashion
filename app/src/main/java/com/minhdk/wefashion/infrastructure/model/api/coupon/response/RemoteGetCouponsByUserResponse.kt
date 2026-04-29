package com.minhdk.wefashion.infrastructure.model.api.coupon.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteGetCouponsByUserResponse(
    @SerializedName("user_id")
    @Expose
    val userId: Int,
    @SerializedName("coupons")
    @Expose
    val coupons: List<RemoteCoupon>
)

