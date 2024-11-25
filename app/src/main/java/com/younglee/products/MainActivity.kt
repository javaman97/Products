package com.aman.products

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.products.R
import com.aman.products.databinding.ActivityMainBinding
import com.younglee.products.paging.ProductPagingAdapter
import com.younglee.products.repository.ProductRepository
import com.younglee.products.viewmodel.ProductViewModel
import com.younglee.products.viewmodel.ProductViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private  lateinit var productPagingAdapter: ProductPagingAdapter
    private lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            binding.productRcV.layoutManager = LinearLayoutManager(this@MainActivity)

            productPagingAdapter = ProductPagingAdapter()

            binding.productRcV.adapter = productPagingAdapter
        }


        val productRepository = ProductRepository()
        val viewModelFactory = ProductViewModelFactory(productRepository)
        productViewModel = ViewModelProvider(this,viewModelFactory)[ProductViewModel::class.java]


        lifecycleScope.launch {
            productViewModel.pagingProduct.collectLatest { pagingData ->

                productPagingAdapter.submitData(pagingData)
            }
        }

    }
}