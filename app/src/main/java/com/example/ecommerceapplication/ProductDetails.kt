package com.example.ecommerceapplication

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.ecommerceapplication.productdetails.ProductDetailsViewModel
import com.example.ecommerceapplication.repos.ProductsRepository
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java)

        val title = intent.getStringExtra("title") ?: ""

        viewModel.productDetails.observe(this, Observer {
            product_name.text = it.title
            Picasso.get().load(it.photoUrl).into(photo)
            thePriceOfProduct.text = "$${it.price}"
           })

        viewModel.fetchProductDetails(title)

        addToCartButton.setOnClickListener{

        }
        //val photoUrl = intent.getStringExtra("photo_url")

        //val product = ProductsRepository().getProductByName(title)
            //.subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
            //.subscribe({
                //product_name.text = it.title
               // Picasso.get().load(it.photoUrl).into(photo)
               // thePriceOfProduct.text = "$${it.price}"
            //}, {})



        availability.setOnClickListener{
            AlertDialog.Builder(this)
                .setMessage("Hey, $title is in stock!")
                .setPositiveButton("OK") { p0, p1 ->
                }
                .create()
                .show()
        }
    }


}