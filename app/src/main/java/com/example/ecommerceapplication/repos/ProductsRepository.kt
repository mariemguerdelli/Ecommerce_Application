package com.example.ecommerceapplication.repos

import com.example.ecommerceapplication.modal.Product
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class ProductsRepository {

    private fun retrofit(): EcommerceApi {
        return Retrofit.Builder()
            .baseUrl("https://finepointmobile.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(EcommerceApi::class.java)
    }

    suspend fun fetchAllProductsRetrofit(): List<Product>{
        return retrofit().fetchAllProducts()
    }
    suspend fun fetchProduct(productTitle: String): Product{
        return fetchAllProductsRetrofit().first{it.title == productTitle}

    }
   // fun getAllProducts() : Single<List<Product>>{
        //return Single.create<List<Product>>{
           // it.onSuccess(fetchProducts())

        //}


    suspend fun searchForProducts(term: String): List<Product>{
       //return Single.create<List<Product>>{
        return fetchAllProductsRetrofit().filter{it.title.contains(term, true)}
           // it.onSuccess(filteredProduct)
        }



   // fun getProductByName(name: String): Single<Product> {
      // return Single.create<Product>{
           // val product = fetchProducts().first { it.title == name }
            //it.onSuccess(product)
       // }


  // fun fetchProducts(): List<Product>{
        //val json = URL("https://gist.githubusercontent.com/mariemguerdelly/b6f44506f2d114290993230724036e50/raw/743e71c6a49a3ff9ce073d2736bc3f65712c51f1/shopping_products.json").readText()
       // return  Gson().fromJson(json, Array<Product>::class.java).toList()

    //}
}