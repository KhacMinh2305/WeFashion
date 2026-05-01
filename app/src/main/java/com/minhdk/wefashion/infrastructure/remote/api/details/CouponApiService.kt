package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponByIdResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsByShopResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsByUserResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsForOrderResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CouponApiService {

    @GET("/api/coupons")
    suspend fun getCoupons(): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<RemoteGetCouponsResponse>

    @GET("/api/coupons/{id}")
    suspend fun getCouponById(
        @Path("id") id: Int
    ): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<RemoteGetCouponByIdResponse>

    @GET("/api/coupons/shop")
    suspend fun getCouponsByShop(
        @Query("shop_id") shopId: Int
    ): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<RemoteGetCouponsByShopResponse>

    @GET("/api/coupons/user")
    suspend fun getCouponsByUser(
        @Query("user_id") userId: Int
    ): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<RemoteGetCouponsByUserResponse>

    @GET("/api/coupons/order")
    suspend fun getCouponsForOrder(
        @Query("shop_id") shopId: Int
    ): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<RemoteGetCouponsForOrderResponse>
}

