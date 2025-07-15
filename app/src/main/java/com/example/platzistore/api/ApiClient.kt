package com.example.platzistore.api

import com.example.platzistore.model.Products
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("api/v1/products")
    fun getProducts(): Call<List<Products>>
}
