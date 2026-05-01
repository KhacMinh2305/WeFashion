package com.minhdk.wefashion.di

import android.content.Context
import androidx.room.Room
import com.minhdk.wefashion.infrastructure.database.room.WeFashionDatabase
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoCategory
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
    fun provideDatabase(@ApplicationContext context: Context): WeFashionDatabase {
        return Room.databaseBuilder(
            context,
            WeFashionDatabase::class.java,
            "wefashiondatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDaoCategory(
        db: WeFashionDatabase
    ): DaoCategory {
        return db.daoCategory()
    }

}