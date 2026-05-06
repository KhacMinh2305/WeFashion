package com.minhdk.wefashion.domain.data.coupon

data class DtoCoupon(
    val id: Int,
    val shopId: Int,
    val name: String?,
    val bannerUrl: String?,
    val discountValue: Int,
    val discountType: String?,
    val amount: Int,
    val createdAt: String?,
    val expiredAt: String?,
    val maxDiscount: Int,
    val minOrderValue: Int
)

data class DtoCouponsByShop(
    val shopId: Int,
    val coupons: List<DtoCoupon>
)

data class DtoCouponsByUser(
    val userId: Int,
    val coupons: List<DtoCoupon>
)
