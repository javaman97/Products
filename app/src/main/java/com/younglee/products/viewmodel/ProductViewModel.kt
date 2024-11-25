package com.younglee.products.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.younglee.products.model.ProductX
import com.younglee.products.paging.ProductPagingSource
import com.younglee.products.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductViewModel(private val productRepository: ProductRepository):ViewModel() {

    val pagingProduct : Flow<PagingData<ProductX>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ProductPagingSource(productRepository)
        }
    ).flow.cachedIn(viewModelScope)

}