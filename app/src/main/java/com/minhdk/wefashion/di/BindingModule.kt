package com.minhdk.wefashion.di

import com.minhdk.wefashion.domain.repository.AuthenticationRepository
import com.minhdk.wefashion.domain.repository.CategoryRepository
import com.minhdk.wefashion.infrastructure.datasource.authentication.RemoteAuthenticationDataSource
import com.minhdk.wefashion.infrastructure.datasource.authentication.RemoteAuthenticationDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.category.local.LocalCategoryDataSource
import com.minhdk.wefashion.infrastructure.datasource.category.local.LocalCategoryDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.category.remote.RemoteCategoryDataSource
import com.minhdk.wefashion.infrastructure.datasource.category.remote.RemoteCategoryDataSourceImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.AuthenticationRepositoryImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Binds
    @Singleton
    abstract fun bindRemoteAuthenticationDataSource(
        impl: RemoteAuthenticationDataSourceImpl
    ): RemoteAuthenticationDataSource

    @Binds
    @Singleton
    abstract fun bindAuthenticationRepository(
        impl: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    @Singleton
    abstract fun provideLocalCategoryDataSource(
        impl: LocalCategoryDataSourceImpl
    ): LocalCategoryDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteCategoryDataSource(
        impl: RemoteCategoryDataSourceImpl
    ): RemoteCategoryDataSource

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

}