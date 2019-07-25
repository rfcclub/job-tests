package com.example.namshopify.data

import com.squareup.moshi.Json


data class ProductVariant (
        var id:String,
        var product_id:String,
        var title:String,
        var price: Double,
        var position: String,
        var taxable: Boolean,
        var grams:String,
        var weight:String,
        var weight_unit:String,
        var inventory_quantity: String
) { }