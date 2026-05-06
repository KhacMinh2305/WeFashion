package com.minhdk.wefashion.di

import com.minhdk.wefashion.domain.repository.AccountRepository
import com.minhdk.wefashion.domain.repository.AuthenticationRepository
import com.minhdk.wefashion.domain.repository.CategoryRepository
import com.minhdk.wefashion.domain.repository.CouponRepository
import com.minhdk.wefashion.domain.repository.ProductRepository
import com.minhdk.wefashion.domain.repository.UserRepository
import com.minhdk.wefashion.infrastructure.datasource.account.local.LocalAccountDataSource
import com.minhdk.wefashion.infrastructure.datasource.account.local.LocalAccountDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.account.remote.RemoteAccountDataSource
import com.minhdk.wefashion.infrastructure.datasource.account.remote.RemoteAccountDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.authentication.RemoteAuthenticationDataSource
import com.minhdk.wefashion.infrastructure.datasource.authentication.RemoteAuthenticationDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.category.local.LocalCategoryDataSource
import com.minhdk.wefashion.infrastructure.datasource.category.local.LocalCategoryDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.category.remote.RemoteCategoryDataSource
import com.minhdk.wefashion.infrastructure.datasource.category.remote.RemoteCategoryDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.coupon.remote.RemoteCouponDataSource
import com.minhdk.wefashion.infrastructure.datasource.coupon.remote.RemoteCouponDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.product.local.LocalProductDataSource
import com.minhdk.wefashion.infrastructure.datasource.product.local.LocalProductDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.product.remote.RemoteProductDataSource
import com.minhdk.wefashion.infrastructure.datasource.product.remote.RemoteProductDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.user.local.LocalUserDataSource
import com.minhdk.wefashion.infrastructure.datasource.user.local.LocalUserDataSourceImpl
import com.minhdk.wefashion.infrastructure.datasource.user.remote.RemoteUserDataSource
import com.minhdk.wefashion.infrastructure.datasource.user.remote.RemoteUserDataSourceImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.AccountRepositoryImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.AuthenticationRepositoryImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.CategoryRepositoryImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.CouponRepositoryImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.ProductRepositoryImpl
import com.minhdk.wefashion.infrastructure.repositoryimpl.UserRepositoryImpl
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
    abstract fun provideLocalAccountDataSource(
        impl: LocalAccountDataSourceImpl
    ): LocalAccountDataSource

    @Binds
    @Singleton
    abstract fun provideRemoteAccountDataSource(
        impl: RemoteAccountDataSourceImpl
    ): RemoteAccountDataSource

    @Binds
    @Singleton
    abstract fun bindAccountRepository(
        impl: AccountRepositoryImpl
    ): AccountRepository

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

    @Binds
    @Singleton
    abstract fun bindRemoteCouponDataSource(
        impl: RemoteCouponDataSourceImpl
    ): RemoteCouponDataSource

    @Binds
    @Singleton
    abstract fun bindCouponRepository(
        impl: CouponRepositoryImpl
    ): CouponRepository

    @Binds
    @Singleton
    abstract fun bindLocalUserDataSource(
        impl: LocalUserDataSourceImpl
    ): LocalUserDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteUserDataSource(
        impl: RemoteUserDataSourceImpl
    ): RemoteUserDataSource

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindLocalProductDataSource(
        impl: LocalProductDataSourceImpl
    ): LocalProductDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteProductDataSource(
        impl: RemoteProductDataSourceImpl
    ): RemoteProductDataSource

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

}