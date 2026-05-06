package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.coupon.DtoCoupon
import com.minhdk.wefashion.domain.data.coupon.DtoCouponsByShop
import com.minhdk.wefashion.domain.data.coupon.DtoCouponsByUser
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteCoupon
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponByIdResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsByShopResponse
import com.minhdk.wefashion.infrastructure.remote.model.api.coupon.RemoteGetCouponsByUserResponse

fun RemoteCoupon.toDtoCoupon(): DtoCoupon {
    return DtoCoupon(
        id = id ?: throw NoSuchElementException("Coupon id is null"),
        shopId = shopId ?: throw NoSuchElementException("Coupon shopId is null"),
        name = name,
        bannerUrl = bannerUrl,
        discountValue = discountValue ?: throw NoSuchElementException("Coupon discountValue is null"),
        discountType = discountType,
        amount = amount ?: throw NoSuchElementException("Coupon amount is null"),
        createdAt = createdAt,
        expiredAt = expiredAt,
        maxDiscount = maxDiscount ?: throw NoSuchElementException("Coupon maxDiscount is null"),
        minOrderValue = minOrderValue ?: throw NoSuchElementException("Coupon minOrderValue is null")
    )
}

fun RemoteGetCouponByIdResponse.toDtoCoupon(): DtoCoupon {
    return DtoCoupon(
        id = id,
        shopId = shopId,
        name = name,
        bannerUrl = bannerUrl,
        discountValue = discountValue,
        discountType = discountType,
        amount = amount,
        createdAt = createdAt,
        expiredAt = expiredAt,
        maxDiscount = maxDiscount,
        minOrderValue = minOrderValue
    )
}

fun List<RemoteCoupon>.toDtoCoupons(): List<DtoCoupon> {
    return map { it.toDtoCoupon() }
}

fun RemoteGetCouponsByShopResponse.toDtoCouponsByShop(): DtoCouponsByShop {
    return DtoCouponsByShop(
        shopId = shopId,
        coupons = coupons.toDtoCoupons()
    )
}

fun RemoteGetCouponsByUserResponse.toDtoCouponsByUser(): DtoCouponsByUser {
    return DtoCouponsByUser(
        userId = userId,
        coupons = coupons.toDtoCoupons()
    )
}
