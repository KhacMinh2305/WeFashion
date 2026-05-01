package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponByIdResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponsByShopResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponsByUserResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponsForOrderResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponsResponse
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CouponApiService {

    @GET("/api/coupons")
    suspend fun getCoupons(): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponsResponse>

    @GET("/api/coupons/{id}")
    suspend fun getCouponById(
        @Path("id") id: Int
    ): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponByIdResponse>

    @GET("/api/coupons/shop")
    suspend fun getCouponsByShop(
        @Query("shop_id") shopId: Int
    ): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponsByShopResponse>

    @GET("/api/coupons/user")
    suspend fun getCouponsByUser(
        @Query("user_id") userId: Int
    ): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponsByUserResponse>

    @GET("/api/coupons/order")
    suspend fun getCouponsForOrder(
        @Query("shop_id") shopId: Int
    ): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.coupon.response.RemoteGetCouponsForOrderResponse>
}

