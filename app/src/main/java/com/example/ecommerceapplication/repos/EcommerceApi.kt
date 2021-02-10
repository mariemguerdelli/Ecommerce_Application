package com.example.ecommerceapplication.repos

import com.example.ecommerceapplication.modal.Product
import retrofit2.http.GET

interface EcommerceApi {

    @GET("api/ecommerce/v1/allProducts")
    suspend fun fetchAllProducts(): List<Product>

}