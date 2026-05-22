package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.cart.DtoCart
import com.minhdk.wefashion.domain.data.order.DtoPaymentLink
import com.minhdk.wefashion.domain.data.order.DtoOrder

interface OrderRepository {

    suspend fun createOrder(
        id: Int,
        discount: Int,
        shippingFee: Int,
        total: Int,
        userId: Int,
        addressId: Int,
        cart: DtoCart
    ): RequestResult<DtoPaymentLink>

    suspend fun getOrders(userId: Int): RequestResult<List<DtoOrder>>

}