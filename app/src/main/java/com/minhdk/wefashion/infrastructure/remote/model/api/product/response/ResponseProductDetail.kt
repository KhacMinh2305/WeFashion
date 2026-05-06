package com.minhdk.wefashion.infrastructure.remote.model.api.product.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseProductDetail(
	@SerializedName("product")
	@Expose
	val product: ResponseProductDetailProduct?,
	@SerializedName("sku")
	@Expose
	val sku: List<ResponseProductSku>?,
	@SerializedName("shop")
	@Expose
	val shop: ResponseProductShop?
)

