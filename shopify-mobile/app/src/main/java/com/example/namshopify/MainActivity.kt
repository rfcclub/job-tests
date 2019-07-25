package com.example.namshopify

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.namshopify.data.ProductList
import com.example.namshopify.viewmodel.TagViewModel
import com.example.namshopify.viewsupport.TagAdapter

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var tagViewModel: TagViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var tagAdapter: TagAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tagViewModel = ViewModelProviders.of(this).get(TagViewModel::class.java)
        viewManager = LinearLayoutManager(this)
        tagAdapter = TagAdapter(ProductList(listOf()), this) // create empty view adapter
        recyclerView = findViewById(R.id.tag_list)
        recyclerView.apply {
            layoutManager = viewManager;
            adapter = tagAdapter
        }
        tagViewModel.getProducts().observe(this, Observer {
            tagAdapter.setData(it?: ProductList(listOf()))
        })

        tagViewModel.loadProducts()

    }

    override fun onClick(v: View?) {
        val intent: Intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("tag", (v as TextView).text)
        startActivity(intent)
    }
}
