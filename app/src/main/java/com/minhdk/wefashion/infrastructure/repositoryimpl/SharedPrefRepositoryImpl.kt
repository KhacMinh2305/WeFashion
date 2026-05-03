package com.minhdk.wefashion.infrastructure.repositoryimpl

import android.content.Context
import com.minhdk.wefashion.di.getAccessor
import com.minhdk.wefashion.di.getSharedPref
import com.minhdk.wefashion.domain.repository.SharedPrefRepository
import com.minhdk.wefashion.infrastructure.database.shared.AppSharedPref
import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPref


class SharedPrefRepositoryImpl (val context: Context): SharedPrefRepository {

    private inline fun <reified T: SharedPref> access(executor: T.() -> Unit) {
        with(getAccessor(context).getSharedPref<T>()) {
            executor()
        }
    }

    private suspend inline fun <reified T: SharedPref> suspendAccess(crossinline executor: suspend T.() -> Unit) {
        with(getAccessor(context).getSharedPref<T>()) {
            executor()
        }
    }

    override fun accessAppSharedPref(executor: AppSharedPref.() -> Unit) {
        access<AppSharedPref>(executor)
    }

    override suspend fun accessAppSharedPrefSuspend(executor: suspend AppSharedPref.() -> Unit) {
        suspendAccess<AppSharedPref>(executor)
    }
}