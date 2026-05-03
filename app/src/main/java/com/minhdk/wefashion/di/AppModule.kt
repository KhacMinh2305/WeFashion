package com.minhdk.wefashion.di

import android.content.Context
import androidx.room.Room
import com.minhdk.wefashion.domain.repository.SharedPrefRepository
import com.minhdk.wefashion.infrastructure.database.room.WeFashionDatabase
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoAccount
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoCategory
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoUser
import com.minhdk.wefashion.infrastructure.database.shared.AppSharedPref
import com.minhdk.wefashion.infrastructure.database.shared.AppSharedPrefImpl
import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPref
import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPrefImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.SharedPrefRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppSharedPref(
        @ApplicationContext context: Context
    ): AppSharedPref {
        return AppSharedPrefImpl(context, "APP_SHARED_PREF")
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeFashionDatabase {
        return Room.databaseBuilder(
                context,
                WeFashionDatabase::class.java,
                "wefashiondatabase"
            ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    @Singleton
    fun provideDaoCategory(
        db: WeFashionDatabase
    ): DaoCategory {
        return db.daoCategory()
    }

    @Provides
    @Singleton
    fun provideDaoAccount(
        db: WeFashionDatabase
    ): DaoAccount {
        return db.daoAccount()
    }

    @Provides
    @Singleton
    fun provideDaoUser(
        db: WeFashionDatabase
    ): DaoUser {
        return db.daoUser()
    }

    @Provides
    @Singleton
    fun provideSharedRepository(
        @ApplicationContext context: Context
    ): SharedPrefRepository {
        return SharedPrefRepositoryImpl(context)
    }

}