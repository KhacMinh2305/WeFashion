package com.minhdk.wefashion.di

import com.minhdk.wefashion.domain.repository.authentication.AuthenticationRepository
import com.minhdk.wefashion.infrastructure.datasource.authentication.RemoteAuthenticationDataSource
import com.minhdk.wefashion.infrastructure.datasource.authentication.RemoteAuthenticationDataSourceImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.AuthenticationRepositoryImpl
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

}