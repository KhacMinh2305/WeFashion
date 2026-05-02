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
    object Skip: Onboarding()

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
    object Verification: Authentication()
}

@Serializable
sealed class Home {

    @Serializable
    object HomeFlow: Home()

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
    object OrderFlow: Order()

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
    object ContactFlow: Contact()

    @Serializable
    object Message: Contact()

    @Serializable
    object Assistant: Contact()
}

@Serializable
sealed class Setting {

    @Serializable
    object SettingFlow: Setting()

    @Serializable
    object General: Setting()
}