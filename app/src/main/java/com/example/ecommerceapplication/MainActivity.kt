package com.example.ecommerceapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.room.Room
import com.example.ecommerceapplication.cart.CartActivity

import kotlinx.android.synthetic.main.activity_main.*
import com.example.ecommerceapplication.database.AppDatabase
import com.example.ecommerceapplication.database.CartModel
import com.example.ecommerceapplication.database.ProductFromDatabase
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        doAsync {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "com.example.ecommerceapplication.database-name"
            ).build()
            db.productDao().insertAll(ProductFromDatabase( null, "Socks  -  one dozen", 1.99 ))
            val products = db.productDao().getAll()

            val cart = db.cartDao()

            cart.insertAll(CartModel(null, "Test product",12.99,3 ))

            val allCartItems = cart.getAll()

            uiThread {
                d("mariem", "products size? ${products.size} ${products[0].title}")

                allCartItems.forEach{
                   d("mariem", "item in cart: ${it.title} ${it.price}")
                }
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MainFragment()).commit()

        navigationView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.actionHome -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MainFragment()).commit()

            }
                R.id.actionwishlist -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, Jeans_Fragment()).commit()
                }

                R.id.actionNotification ->
                {d ("mariem" , "Notification was pressed!")
            }
                R.id.actionAccount -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, Account_Fragment()).commit()
                }
                R.id.action_settings -> {
                    d("mariem", "Settings was pressed!")
                }

                    R.id.actionAdmin ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
                        AdminFragment()
                    ).commit()
                }
            }

            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }


    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.actionCart) {

            d("mariem", "going to cart")
            startActivity(Intent(this,CartActivity::class.java))
            return true
        }

        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

}
