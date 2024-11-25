package com.younglee.products.api

import com.younglee.products.model.ProductX
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProduct(
        @Query("page") page:Int,
        @Query("pageSize") pageSize:Int
    ): List<ProductX>

}