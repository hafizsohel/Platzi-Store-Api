package com.example.platzistore.model

data class Products(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>
)
