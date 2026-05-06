package com.minhdk.wefashion.infrastructure.repositoryimpl

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.coupon.DtoCoupon
import com.minhdk.wefashion.domain.data.coupon.DtoCouponsByShop
import com.minhdk.wefashion.domain.data.coupon.DtoCouponsByUser
import com.minhdk.wefashion.domain.repository.CouponRepository
import com.minhdk.wefashion.infrastructure.datasource.coupon.remote.RemoteCouponDataSource
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoCoupon
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoCoupons
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoCouponsByShop
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoCouponsByUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouponRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteCouponDataSource
) : CouponRepository {

    override suspend fun getCoupons(): RequestResult<List<DtoCoupon>> {
        return try {
            val coupons = remoteSource.getCoupons().toDtoCoupons()
            RequestResult.Success(coupons)
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun getCouponById(id: Int): RequestResult<DtoCoupon> {
        return try {
            val coupon = remoteSource.getCouponById(id)
                ?: throw NoSuchElementException("Coupon not found for id=$id")
            RequestResult.Success(coupon.toDtoCoupon())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun getCouponsByShop(shopId: Int): RequestResult<DtoCouponsByShop> {
        return try {
            val response = remoteSource.getCouponsByShop(shopId)
                ?: throw NoSuchElementException("Coupons not found for shopId=$shopId")
            RequestResult.Success(response.toDtoCouponsByShop())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun getCouponsByUser(userId: Int): RequestResult<DtoCouponsByUser> {
        return try {
            val response = remoteSource.getCouponsByUser(userId)
                ?: throw NoSuchElementException("Coupons not found for userId=$userId")
            RequestResult.Success(response.toDtoCouponsByUser())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun getCouponsForOrder(shopId: Int): RequestResult<List<DtoCoupon>> {
        return try {
            val coupons = remoteSource.getCouponsForOrder(shopId).toDtoCoupons()
            RequestResult.Success(coupons)
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

}

