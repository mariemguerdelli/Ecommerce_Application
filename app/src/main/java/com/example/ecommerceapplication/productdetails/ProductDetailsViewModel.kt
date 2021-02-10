package com.example.ecommerceapplication.productdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapplication.modal.Product
import com.example.ecommerceapplication.repos.ProductsRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ProductDetailsViewModel : ViewModel() {

    val productDetails = MutableLiveData<Product>()
    fun fetchProductDetails(productTitle: String){
        viewModelScope.launch(Dispatchers.Default){

            productDetails.postValue(ProductsRepository().fetchProduct(productTitle))
        }
    }
}