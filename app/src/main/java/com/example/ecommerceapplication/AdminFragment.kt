package com.example.ecommerceapplication

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.ecommerceapplication.database.AppDatabase
import com.example.ecommerceapplication.database.ProductFromDatabase
import kotlinx.android.synthetic.main.fragment_admin.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdminFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener{
            val title = productTitle.text
            d("mariem", "button pressed with text of $title")

            doAsync {
                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "com.example.ecommerceapplication.database-name"
                ).build()
                db.productDao().insertAll(
                    ProductFromDatabase(
                        null,
                        title.toString(),
                        12.34
                    )
                )


                uiThread {
                    d("mariem","Redirection to home screen")
                }
            }
        }


    }
}