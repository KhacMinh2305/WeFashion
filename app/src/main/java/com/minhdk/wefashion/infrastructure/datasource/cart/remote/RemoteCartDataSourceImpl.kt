package com.minhdk.wefashion.infrastructure.datasource.cart.remote

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.request.RequestUpdateCartSku
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseCart
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseDeleteCartSku
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseUpdateCartSku
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class RemoteCartDataSourceImpl @Inject constructor(
    private val apiService: DataApiService
) : RemoteCartDataSource {

    override suspend fun getCart(userId: Int): ResponseCart? = withContext(Dispatchers.IO) {
        apiService.getCart(userId).data
    }

    override suspend fun updateSkuQuantity(
        userId: Int,
        body: RequestUpdateCartSku
    ): ResponseUpdateCartSku? = withContext(Dispatchers.IO) {
        apiService.updateSkuQuantity(userId, body).data
    }

    override suspend fun deleteSku(userId: Int, sku: Int): ResponseDeleteCartSku? = withContext(Dispatchers.IO) {
        apiService.deleteSku(userId, sku).data
    }
}

