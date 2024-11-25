package com.younglee.products.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.younglee.products.model.ProductX
import com.younglee.products.repository.ProductRepository

class ProductPagingSource(private val productRepository: ProductRepository): PagingSource<Int, ProductX> (){

    override fun getRefreshKey(state: PagingState<Int, ProductX>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductX> {

        return try {
            val currentPage = params.key?:1
            Log.d("Product Paging Source","Loading Page : $currentPage")

            val response = productRepository.getProduct(currentPage, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if(currentPage == 1) null else currentPage - 1 ,
                nextKey = if(response.isEmpty()) null else currentPage + 1
                )
        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}