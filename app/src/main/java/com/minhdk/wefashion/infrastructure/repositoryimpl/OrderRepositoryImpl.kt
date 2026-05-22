package com.minhdk.wefashion.infrastructure.repositoryimpl

import android.util.Log
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.cart.DtoCart
import com.minhdk.wefashion.domain.data.order.DtoPaymentLink
import com.minhdk.wefashion.domain.data.order.DtoOrder
import com.minhdk.wefashion.domain.repository.OrderRepository
import com.minhdk.wefashion.infrastructure.datasource.order.remote.RemoteOrderDataSource
import com.minhdk.wefashion.infrastructure.mapper.helper.order.buildRequestCreateOrderBody
import com.minhdk.wefashion.infrastructure.mapper.model.toDto
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoOrders
import com.minhdk.wefashion.util.helper.logD
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteOrderDataSource
) : OrderRepository {

    override suspend fun createOrder(
        id: Int,
        discount: Int,
        shippingFee: Int,
        total: Int,
        userId: Int,
        addressId: Int,
        cart: DtoCart
    ): RequestResult<DtoPaymentLink> {
        val body = buildRequestCreateOrderBody(discount, shippingFee, total, userId, addressId, cart)
        logD("midas", body.toString())
        try {
            val res = remoteSource.createOrder(id, body)
            val resDto = (res ?: return RequestResult.Error(Exception("Can not create payment link now"))).toDto()!!
            return RequestResult.Success(resDto)
        } catch (e: Exception) {
            return RequestResult.Error(Exception("Something went wrong"))
        }
    }

    override suspend fun getOrders(userId: Int): RequestResult<List<DtoOrder>> {
        return try {
            val res = remoteSource.getOrders(userId)
            RequestResult.Success(res?.toDtoOrders().orEmpty())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }
}