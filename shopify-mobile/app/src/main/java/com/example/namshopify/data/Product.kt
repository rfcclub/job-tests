package com.example.namshopify.data

import com.squareup.moshi.Json


data class Product(
        var id: String,
        var title: String,
        var body_html: String,
        var vendor: String,
        var tags: String,
        var product_type: String,
        var variants: List<ProductVariant>,
        var options: List<ProductOption>,
        var images: List<ProductImage>,
        var image: ProductImage

) {
    fun getTagList(): List<String> {
        var result: List<String> = ArrayList();
        if (!tags.isNullOrEmpty()) {
            result = tags.split(",").map { it.trim() }
        }
        return result;
    }

    fun getInventoryQuantity(): Int {
        var result : Int = 0
        if (variants.isNotEmpty()) {
            result = variants.map { it.inventory_quantity.toInt() }.reduce { first, ele -> first + ele}
        }
        return result
    }

    fun getVariantText(): CharSequence? {
        var result: String = ""
        if (variants.isNotEmpty()) {
            result = variants.map { it.title }.joinToString(", ")
        }
        return result
    }

//    fun getTotalInventoryQuantity() : Int {
//        var totalQty = 0
//        if (variants != null && variants.isNotEmpty()) {
//            variants.forEach {
//                totalQty += it.inventoryQuantity
//            }
//        }
//        return totalQty
//    }
}

