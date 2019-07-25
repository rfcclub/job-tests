package com.example.namshopify.data

import com.squareup.moshi.Json


data class ProductOption (
        var id: String,
        var product_id: String,
        var name: String,
        var position: String,
        var values: List<String>
) {}