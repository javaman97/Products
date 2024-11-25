package com.younglee.products.model

data class ProductX(
    val category: String = "",
    val description: String = "",
    val id: Int = 0,
    val image: String = "",
    val price: Double = 0.0,
    val rating: Rating = Rating(),
    val title: String = ""
)