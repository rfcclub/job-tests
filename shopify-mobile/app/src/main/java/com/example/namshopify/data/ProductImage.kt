package com.example.namshopify.data

import com.squareup.moshi.Json


data class ProductImage (
        var id: String,
        var product_id: String,
        var position: String,
        var width: String,
        var height: String,
        var src: String,
        var admin_graphql_api_id: String
) { }