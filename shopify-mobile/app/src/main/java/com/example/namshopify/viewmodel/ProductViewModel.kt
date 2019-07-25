package com.example.namshopify.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.namshopify.data.Product
import com.example.namshopify.repository.ShopifyRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ProductViewModel : ViewModel() {
    private var shopifyRepository: ShopifyRepository = ShopifyRepository.create()
    private var productsData: MutableLiveData<List<Product>> = MutableLiveData()

    fun getFilteredProducts() : MutableLiveData<List<Product>> {
        return productsData
    }

    fun getProductsByTag(tag:String) {
        doAsync {
            val products: List<Product> = shopifyRepository.getProducts().products.filter { it.tags.indexOf(tag) >=0 }
            uiThread {
                productsData.value = products
            }
        }
    }

}