package com.minhdk.wefashion.presentation.ui.screen.cart.main

import com.minhdk.wefashion.domain.data.cart.DtoCart
import com.minhdk.wefashion.domain.data.coupon.DtoCoupon

data class CartState(
    val isLoading: Boolean = false,
    val isProcessingPayment: Boolean = false,
    val discount: Int?,
    val shippingFee: Int?,
    val total: Int?,
    val cart: DtoCart? = null,
    val selectedSkus: Set<Int> = emptySet(),
    val promoCode: String = "",
    val appliedCoupon: DtoCoupon? = null,
    val discountValue: Int = 0,
    val errorMessage: String? = null
)

sealed class CartIntent {
    data object LoadCart : CartIntent()
    data class ToggleSelectSku(val sku: Int) : CartIntent()
    data class UpdateSkuQuantity(val sku: Int, val change: String, val amount: Int) : CartIntent()
    data class PromoCodeChanged(val value: String) : CartIntent()
    data object ApplyPromo : CartIntent()
    data class Checkout(val addressId: Int?) : CartIntent()
}

sealed class CartEffect {
    data class ShowToast(val message: String) : CartEffect()
    data class StartPaymentProcess(val link: String) : CartEffect()
}
