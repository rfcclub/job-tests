package com.example.namshopify.repository

import com.example.namshopify.api.MyShopifyApi
import com.example.namshopify.data.ProductList

class ShopifyRepository(val myShopifyApi: MyShopifyApi) {
    fun getProducts(): ProductList {
        val page = "1";
        val accessToken = "c32313df0d0ef512ca64d5b336a0d7c6";
        return myShopifyApi.getProducts(page, accessToken).execute().body()?:ProductList(listOf())
    }

    companion object Factory {
        fun create() : ShopifyRepository {
            return ShopifyRepository(MyShopifyApi.create())
        }
    }
}