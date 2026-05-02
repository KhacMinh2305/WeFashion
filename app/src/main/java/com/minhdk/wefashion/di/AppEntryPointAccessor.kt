package com.minhdk.wefashion.di

import com.minhdk.wefashion.infrastructure.database.shared.AppSharedPref
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppEntryPointAccessor {

    fun appSharedPref(): AppSharedPref

}

fun AppEntryPointAccessor.getAccessor(
    context: ApplicationContext
): AppEntryPointAccessor {
    return EntryPoints.get(context, AppEntryPointAccessor::class.java)
}