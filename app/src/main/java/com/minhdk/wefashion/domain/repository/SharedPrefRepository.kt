package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.infrastructure.database.shared.AppSharedPref


interface SharedPrefRepository {

    fun accessAppSharedPref(executor: AppSharedPref.() -> Unit)

    suspend fun accessAppSharedPrefSuspend(executor: suspend AppSharedPref.() -> Unit)

}