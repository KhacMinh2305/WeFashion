package com.minhdk.wefashion.infrastructure.remote.model.api.coupon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteGetCouponsByShopResponse(
    @SerializedName("shop_id")
    @Expose
    val shopId: Int,
    @SerializedName("coupons")
    @Expose
    val coupons: List<RemoteCoupon>
)

