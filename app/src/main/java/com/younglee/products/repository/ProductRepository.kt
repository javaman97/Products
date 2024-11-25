package com.younglee.products.repository

import com.younglee.products.api.RetrofitInstance
import com.younglee.products.model.ProductX


class ProductRepository {

    suspend fun getProduct(page:Int, pageSize:Int):List<ProductX> {
        return RetrofitInstance.api.getProduct(page, pageSize)
    }

}