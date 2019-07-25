package com.example.namshopify.data

data class ProductList(
    var products: List<Product>
) {
    fun getAllTags() : List<String> {
        var tagList : List<String> = ArrayList<String>()
        if (products.isNotEmpty()) {
            tagList = products.flatMap { it.getTagList() }.distinct()
            tagList = tagList.sorted()
        }
        return tagList
    }
}