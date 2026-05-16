package com.minhdk.wefashion.infrastructure.remote.model.api.order.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestCreateOrderBody(

    @SerializedName("discount")
    @Expose
    val discount: Int,

    @SerializedName("shipping_fee")
    @Expose
    val shippingFee: Int,

    @SerializedName("total_price")
    @Expose
    val totalPrice: Int,

    @SerializedName("user_id")
    @Expose
    val userId: Int,

    @SerializedName("address_id")
    @Expose
    val addressId: Int,

    @SerializedName("items")
    @Expose
    val items: List<RequestCreateOrderItemBody>
)

data class RequestCreateOrderItemBody(

    @SerializedName("sku")
    @Expose
    val sku: Int,

    @SerializedName("amount")
    @Expose
    val amount: Int,

    @SerializedName("price")
    @Expose
    val price: Int,

    @SerializedName("product_id")
    @Expose
    val productId: Int,

    @SerializedName("size_id")
    @Expose
    val sizeId: Int,

    @SerializedName("color_id")
    @Expose
    val colorId: Int
)