package com.minhdk.wefashion.presentation.ui.screen.cart.address

import java.io.Serializable

data class SelectedAddress(
    val name: String,
    val detail: String,
    val ward: String,
    val district: String,
    val city: String
) : Serializable

