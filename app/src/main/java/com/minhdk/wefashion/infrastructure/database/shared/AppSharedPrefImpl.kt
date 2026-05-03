package com.minhdk.wefashion.infrastructure.database.shared

import android.content.Context
import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPref
import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPrefHelper
import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPrefImpl
import com.tomtom.sdk.routing.route.Consumption
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

class AppSharedPrefImpl (
    context: Context ,
    name: String
): SharedPrefImpl(context, name), AppSharedPref {

    companion object {
        private const val FIRST_OPEN_APP = "FIRST_OPEN_APP"
    }

    private var helper = SharedPrefHelper(this)

    override fun doNoThing() {}

    override var firstOpenApp: Boolean
        get() = getBoolean(FIRST_OPEN_APP, true)
        set(value) = putBoolean(FIRST_OPEN_APP, value)

}