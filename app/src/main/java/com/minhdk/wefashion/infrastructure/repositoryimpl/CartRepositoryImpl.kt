package com.minhdk.wefashion.infrastructure.repositoryimpl

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.cart.DtoCart
import com.minhdk.wefashion.domain.data.cart.DtoCartDeleteResult
import com.minhdk.wefashion.domain.data.cart.DtoCartSkuUpdateResult
import com.minhdk.wefashion.domain.repository.CartRepository
import com.minhdk.wefashion.infrastructure.datasource.cart.remote.RemoteCartDataSource
import com.minhdk.wefashion.infrastructure.mapper.helper.cart.buildUpdateCartSkuRequest
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoCart
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoCartDeleteResult
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoCartSkuUpdateResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteCartDataSource
) : CartRepository {

    override suspend fun getCart(userId: Int): RequestResult<DtoCart?> {
        return try {
            val response = remoteSource.getCart(userId)
            RequestResult.Success(response?.toDtoCart())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun updateSkuQuantity(
        userId: Int,
        sku: Int,
        change: String,
        amount: Int
    ): RequestResult<DtoCartSkuUpdateResult> {
        return try {
            val body = buildUpdateCartSkuRequest(sku, change, amount)
            val response = remoteSource.updateSkuQuantity(userId, body)
                ?: throw Exception("Response is null")
            RequestResult.Success(response.toDtoCartSkuUpdateResult())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun deleteSku(userId: Int, sku: Int): RequestResult<DtoCartDeleteResult> {
        return try {
            val response = remoteSource.deleteSku(userId, sku)
                ?: throw Exception("Response is null")
            RequestResult.Success(response.toDtoCartDeleteResult())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }
}

