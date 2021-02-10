package com.example.ecommerceapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapplication.modal.Product
import com.example.ecommerceapplication.repos.ProductsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {
    lateinit var  viewModel: MainFragmentViewModel

   // @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        //root.progressBar.visibility = View.GONE
        val categories = listOf("Jeans", "Socks", "Skirts", "Dresses", "Jeans", "Socks", "Pants","Jackets")
        root.categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(MainFragmentViewModel::class.java)
        viewModel.products.observe(requireActivity(), Observer {
            loadRecyclerView(it)
        })

        viewModel.setup()
        //val productsRepository = ProductsRepository().getAllProducts()
       // loadRecyclerView(productsRepository)

        searchButton.setOnClickListener{
            viewModel.search(searchTerm.text.toString())
            //loadRecyclerView(ProductsRepository().searchForProducts(searchTerm.text.toString()))
        }
        searchTerm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.search(searchTerm.text.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })


    }
    private fun loadRecyclerView(products: List<Product>) {
                recycler_view.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(products){ extraTitle, _ , photoView ->
                        val intent = Intent (activity, ProductDetails::class.java)
                        intent.putExtra("title", extraTitle)
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity as AppCompatActivity, photoView, "PhotoToAnimate")
                        startActivity(intent, options.toBundle())

                    }
                }
                progressBar.visibility = View.GONE
            }
    }
