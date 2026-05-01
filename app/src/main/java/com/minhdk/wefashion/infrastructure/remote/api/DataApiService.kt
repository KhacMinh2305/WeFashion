package com.minhdk.wefashion.infrastructure.remote.api


import com.minhdk.wefashion.infrastructure.remote.api.details.AccountApiService
import com.minhdk.wefashion.infrastructure.remote.api.details.CategoryApiService
import com.minhdk.wefashion.infrastructure.remote.api.details.CouponApiService

interface DataApiService : AccountApiService, CategoryApiService, CouponApiService {
    
}