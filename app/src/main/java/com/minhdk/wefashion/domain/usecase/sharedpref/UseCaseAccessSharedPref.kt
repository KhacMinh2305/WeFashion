package com.minhdk.wefashion.domain.usecase.sharedpref

import com.minhdk.wefashion.domain.repository.SharedPrefRepository


suspend fun getFirstOpenAppUseCase(
    sharedPref: SharedPrefRepository,
    action: suspend (Boolean) -> Unit
) {
    sharedPref.accessAppSharedPrefSuspend {
        action.invoke(firstOpenApp)
    }
}

fun setFirstOpenAppUseCase(
    sharedPref: SharedPrefRepository,
    value: Boolean
) {
    sharedPref.accessAppSharedPref {
        firstOpenApp = value
    }
}