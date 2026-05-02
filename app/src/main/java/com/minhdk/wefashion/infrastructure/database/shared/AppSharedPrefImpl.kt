package com.minhdk.wefashion.infrastructure.database.shared

import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPref
import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPrefHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSharedPrefImpl @Inject constructor(
    private val accessor: SharedPref
): AppSharedPref {

    companion object {
        private const val FIRST_OPEN_APP = "FIRST_OPEN_APP"
    }

    private var helper = SharedPrefHelper(accessor)

    override var firstOpenApp: Boolean
        get() = accessor.getBoolean(FIRST_OPEN_APP, true)
        set(value) = accessor.putBoolean(FIRST_OPEN_APP, value)

}