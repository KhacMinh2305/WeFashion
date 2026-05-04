package com.minhdk.wefashion.domain.usecase.sharedpref

import com.minhdk.wefashion.domain.repository.SharedPrefRepository


suspend fun getFirstTravelOnboardingUseCase(
    sharedPref: SharedPrefRepository,
    action: suspend (Boolean) -> Unit
) {
    sharedPref.accessAppSharedPrefSuspend {
        action.invoke(watchOnboarding)
    }
}

fun setFirstTravelOnboardingUseCase(
    sharedPref: SharedPrefRepository,
    value: Boolean
) {
    sharedPref.accessAppSharedPref {
        watchOnboarding = value
    }
}