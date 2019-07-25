package com.example.namshopify.api

import com.example.namshopify.data.ProductList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyShopifyApi {
    @GET("products.json")
    fun getProducts(@Query("page") id:String, @Query("access_token") accessToken: String) : Call<ProductList>

    companion object Factory {
        fun create() : MyShopifyApi {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("https://shopicruit.myshopify.com/admin/")
                    .build()

            return retrofit.create(MyShopifyApi::class.java);
        }
    }
}