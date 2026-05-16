package com.minhdk.wefashion.infrastructure.remote.model.api.order.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseCreateOrder(

    @SerializedName("order_id")
    @Expose
    val orderId: Int?,

    @SerializedName("checkout_url")
    @Expose
    val checkoutUrl: String?,

    @SerializedName("qr_code")
    @Expose
    val qrCode: String?
)