package com.minhdk.wefashion.infrastructure.remote


import com.minhdk.wefashion.infrastructure.remote.dataService.AccountApiService
import com.minhdk.wefashion.infrastructure.remote.dataService.CategoryApiService
import com.minhdk.wefashion.infrastructure.remote.dataService.CouponApiService

interface DataApiService : AccountApiService, CategoryApiService, CouponApiService {
    
}