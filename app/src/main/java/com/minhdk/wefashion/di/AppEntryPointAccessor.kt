package com.minhdk.wefashion.di

import android.content.Context
import com.minhdk.wefashion.infrastructure.database.shared.AppSharedPref
import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPref
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppEntryPointAccessor {

    fun appSharedPref(): AppSharedPref

}

fun getAccessor(
    context: Context
): AppEntryPointAccessor {
    return EntryPoints.get(context.applicationContext, AppEntryPointAccessor::class.java)
}

inline fun <reified T: SharedPref> AppEntryPointAccessor.getSharedPref(): T {
    return when(T::class) {
        AppSharedPref::class -> appSharedPref() as T
        else -> throw IllegalArgumentException("Invalid SharedPref type")
    }
}