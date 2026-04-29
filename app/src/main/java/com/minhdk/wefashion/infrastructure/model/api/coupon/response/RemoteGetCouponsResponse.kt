package com.minhdk.wefashion.infrastructure.model.api.coupon.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteGetCouponsResponse(
    @SerializedName("coupons")
    @Expose
    val coupons: List<RemoteCoupon>
)

