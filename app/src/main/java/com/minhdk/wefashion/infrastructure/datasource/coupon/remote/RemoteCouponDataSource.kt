package com.minhdk.wefashion.infrastructure.datasource.coupon.remote

import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteCoupon
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponByIdResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsByShopResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsByUserResponse

interface RemoteCouponDataSource {

    suspend fun getCoupons(): List<RemoteCoupon>

    suspend fun getCouponById(id: Int): RemoteGetCouponByIdResponse?

    suspend fun getCouponsByShop(shopId: Int): RemoteGetCouponsByShopResponse?

    suspend fun getCouponsByUser(userId: Int): RemoteGetCouponsByUserResponse?

    suspend fun getCouponsForOrder(shopId: Int): List<RemoteCoupon>

}
