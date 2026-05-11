package com.minhdk.wefashion.presentation.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Onboarding {

    @Serializable
    object OnboardingFlow: Onboarding()

    @Serializable
    object Splash: Onboarding()

    @Serializable
    object Introduce: Onboarding()

    @Serializable
    object End: Onboarding()

}

@Serializable
sealed class Authentication {

    @Serializable
    object AuthFlow: Authentication()

    @Serializable
    object Register: Authentication()

    @Serializable
    object Login: Authentication()

    @Serializable
    object ForgotPassword: Authentication()

    @Serializable
    data class Verification(
        val email: String,
        val credential: String
    ): Authentication()

    @Serializable
    object Reset: Authentication()

    @Serializable
    data object End: Authentication()

    @Serializable
    object Back: Authentication()
}

@Serializable
sealed class Home {

    @Serializable
    object HomeFlow: Home()

    @Serializable
    data object Main: Home()

    @Serializable
    object Search: Home()

    @Serializable
    data class ProductDetail(val productId: Int): Home()

    @Serializable
    object Back: Home()
}

@Serializable
sealed class Order {

    @Serializable
    object OrderFlow: Order()

    @Serializable
    object MyOrder: Order()

    @Serializable
    data class OrderDetail(val orderId: Int): Order()

    @Serializable
    data class OrderTracking(val orderId: Int): Order()

}

@Serializable
sealed class Cart {

    @Serializable
    object CartFlow: Cart()

    @Serializable
    object CartMain: Cart()

    @Serializable
    data class Coupon(val orderTotal: Int): Cart()
}

@Serializable
sealed class Setting {

    @Serializable
    object SettingFlow: Setting()

    @Serializable
    object General: Setting()
}