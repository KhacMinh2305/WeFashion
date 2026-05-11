package com.minhdk.wefashion.infrastructure.remote.api


import com.minhdk.wefashion.infrastructure.remote.api.details.AccountApiService
import com.minhdk.wefashion.infrastructure.remote.api.details.AddressApiService
import com.minhdk.wefashion.infrastructure.remote.api.details.CategoryApiService
import com.minhdk.wefashion.infrastructure.remote.api.details.CouponApiService
import com.minhdk.wefashion.infrastructure.remote.api.details.ProductApiService
import com.minhdk.wefashion.infrastructure.remote.api.details.SearchApiService
import com.minhdk.wefashion.infrastructure.remote.api.details.UserApiService
import com.minhdk.wefashion.infrastructure.remote.api.details.CartApiService

interface DataApiService :
    AddressApiService,
    CategoryApiService,
    CouponApiService,
    AccountApiService,
    UserApiService,
    ProductApiService,
    SearchApiService,
    CartApiService {}
