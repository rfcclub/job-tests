package com.example.namshopify.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.namshopify.data.Product
import com.example.namshopify.data.ProductImage
import com.example.namshopify.data.ProductList
import com.example.namshopify.repository.ShopifyRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class ProductViewModelTest {

    @MockK
    lateinit var repository : ShopifyRepository
    @MockK
    lateinit var productsData : MutableLiveData<List<Product>>
    @InjectMockKs(overrideValues = true)
    var productViewModel = ProductViewModel()


    @Test
    fun testGetFilterProducts() {
        assertNotNull(productViewModel.getFilteredProducts())
    }

    @Test
    fun testGetProductByTags() {
        val filterText = "ABC"
        every {
            repository.getProducts()
        } returns createTestProduct(filterText)
        productViewModel.getProductsByTag(filterText)
        // when update data on ui thread it fails but the service call is verified.
        coVerify {
            repository.getProducts()
        }
    }

    private fun createTestProduct(filterText : String ) : ProductList {
        var productImage = ProductImage("1", "1", "1", "100", "100", "http://abc.jpg", "")
        var product = Product("1", filterText, filterText, filterText, filterText, filterText, listOf(), listOf(), listOf(), productImage)
        return ProductList(listOf(product))
    }
}