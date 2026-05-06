package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.coupon.DtoCoupon
import com.minhdk.wefashion.domain.data.coupon.DtoCouponsByShop
import com.minhdk.wefashion.domain.data.coupon.DtoCouponsByUser

interface CouponRepository {

    suspend fun getCoupons(): RequestResult<List<DtoCoupon>>

    suspend fun getCouponById(id: Int): RequestResult<DtoCoupon>

    suspend fun getCouponsByShop(shopId: Int): RequestResult<DtoCouponsByShop>

    suspend fun getCouponsByUser(userId: Int): RequestResult<DtoCouponsByUser>

    suspend fun getCouponsForOrder(shopId: Int): RequestResult<List<DtoCoupon>>

}
