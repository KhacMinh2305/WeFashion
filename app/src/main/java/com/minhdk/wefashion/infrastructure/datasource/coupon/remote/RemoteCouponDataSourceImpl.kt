package com.minhdk.wefashion.infrastructure.datasource.coupon.remote

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteCoupon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCouponDataSourceImpl @Inject constructor(
    private val apiService: DataApiService
) : RemoteCouponDataSource {

    override suspend fun getCoupons() = withContext(Dispatchers.IO) {
        return@withContext apiService.getCoupons().data?.coupons.orEmpty().excludeExpiredCoupons()
    }

    override suspend fun getCouponById(id: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getCouponById(id).data
    }

    override suspend fun getCouponsByShop(shopId: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getCouponsByShop(shopId).data
    }

    override suspend fun getCouponsByUser(userId: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getCouponsByUser(userId).data
    }

    override suspend fun getCouponsForOrder(shopId: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getCouponsForOrder(shopId).data?.coupons.orEmpty()
    }

    private fun List<RemoteCoupon>.excludeExpiredCoupons(): List<RemoteCoupon> {
        val now = System.currentTimeMillis()
        return filter { coupon ->
            coupon.expiredAt?.let {
                try {
                    val expiredTime = java.time.Instant.parse(it).toEpochMilli()
                    expiredTime > now
                } catch (e: Exception) {
                    true
                }
            } ?: true
        }
    }
}
