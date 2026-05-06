package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponByIdResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsByShopResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsByUserResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsForOrderResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsResponse
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CouponApiService {

    @GET("/api/coupons")
    suspend fun getCoupons(): BaseResponse<RemoteGetCouponsResponse>

    @GET("/api/coupons/{id}")
    suspend fun getCouponById(
        @Path("id") id: Int
    ): BaseResponse<RemoteGetCouponByIdResponse>

    @GET("/api/coupons/shop")
    suspend fun getCouponsByShop(
        @Query("shop_id") shopId: Int
    ): BaseResponse<RemoteGetCouponsByShopResponse>

    @GET("/api/coupons/user")
    suspend fun getCouponsByUser(
        @Query("user_id") userId: Int
    ): BaseResponse<RemoteGetCouponsByUserResponse>

    @GET("/api/coupons/order")
    suspend fun getCouponsForOrder(
        @Query("shop_id") shopId: Int
    ): BaseResponse<RemoteGetCouponsForOrderResponse>
}

