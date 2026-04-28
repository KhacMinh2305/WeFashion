package com.minhdk.wefashion.presentation.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Authentication {
    @Serializable
    object Onboarding: Authentication()

    @Serializable
    object Register: Authentication()

    @Serializable
    object Login: Authentication()

    object ForgotPassword: Authentication()

    @Serializable
    object Verification: Authentication()
}

@Serializable
sealed class Home {

    @Serializable
    object Main: Home()

    @Serializable
    object Profile: Home()

    @Serializable
    object Search: Home()

    @Serializable
    object Notification: Home()

    @Serializable
    data class ListProducts(val type: String): Home()
}

@Serializable
sealed class Order {

    @Serializable
    object MyOrder: Order()

    @Serializable
    data class OrderDetail(val orderId: Int): Order()

    @Serializable
    data class OrderTracking(val orderId: Int): Order()

    @Serializable
    object Cart: Order()

}

@Serializable
sealed class Contact {

    @Serializable
    object Message: Contact()

    object Assistant: Contact()
}

@Serializable
sealed class Setting {
    @Serializable
    object General: Setting()
}