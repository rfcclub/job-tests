package com.example.namshopify.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.namshopify.data.ProductList
import com.example.namshopify.repository.ShopifyRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TagViewModel : ViewModel() {
    private var shopifyRepository: ShopifyRepository = ShopifyRepository.create()
    private var productsData: MutableLiveData<ProductList> = MutableLiveData<ProductList>()

    fun getProducts() : LiveData<ProductList> {
        return productsData
    }

    fun loadProducts() {
        doAsync {
            val productList:ProductList = shopifyRepository.getProducts()
            uiThread {
                productsData.value = productList
            }
        }

    }
}