package com.minhdk.wefashion.infrastructure.remote.model.api.order.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseOrderList(
    @SerializedName("orders")
    @Expose
    val orders: List<ResponseOrder>?
)

data class ResponseOrder(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("discount")
    @Expose
    val discount: Int?,
    @SerializedName("shipping_fee")
    @Expose
    val shippingFee: Int?,
    @SerializedName("total_price")
    @Expose
    val totalPrice: Int?,
    @SerializedName("order_state")
    @Expose
    val orderState: Int?,
    @SerializedName("shipping_state")
    @Expose
    val shippingState: Int?,
    @SerializedName("created_at")
    @Expose
    val createdAt: String?,
    @SerializedName("user_id")
    @Expose
    val userId: Int?,
    @SerializedName("address_id")
    @Expose
    val addressId: Int?,
    @SerializedName("payment_id")
    @Expose
    val paymentId: Int?,
    @SerializedName("shipper_id")
    @Expose
    val shipperId: Int?,
    @SerializedName("product_amount")
    @Expose
    val productAmount: Int?
)

