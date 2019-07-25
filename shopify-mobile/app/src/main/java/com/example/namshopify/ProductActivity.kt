package com.example.namshopify

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.example.namshopify.viewmodel.ProductViewModel
import com.example.namshopify.viewsupport.ProductAdapter

class ProductActivity : AppCompatActivity() {

    private lateinit var productViewModel : ProductViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var back_button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java);
        viewManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(listOf()) // initialize view adapter
        recyclerView = findViewById(R.id.product_list)
        recyclerView.apply {
            layoutManager = viewManager;
            adapter = productAdapter
        }
        productViewModel.getFilteredProducts().observe(this, Observer {
            productAdapter.setData(it?: listOf())
        })
        // load products by tag name
        val tagName : String = intent.getStringExtra("tag")
        productViewModel.getProductsByTag(tagName)
        back_button = findViewById(R.id.back_button)
        back_button.setOnClickListener {
            var intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
