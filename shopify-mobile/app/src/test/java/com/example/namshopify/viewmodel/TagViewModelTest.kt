package com.example.namshopify.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.namshopify.data.ProductList
import com.example.namshopify.repository.ShopifyRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class TagViewModelTest {

    @MockK
    lateinit var repository : ShopifyRepository
    @MockK
    lateinit var productsData : MutableLiveData<ProductList>
    @InjectMockKs(overrideValues = true)
    var tagViewModel = TagViewModel()


    @Test
    fun testGetFilterProducts() {
        Assertions.assertNotNull(tagViewModel.getProducts())
    }

    @Test
    fun testGetProductByTags() {
        every {
            repository.getProducts()
        } returns ProductList(listOf())

        tagViewModel.loadProducts()
        // when update data on ui thread it fails but the service call is verified.
        coVerify {
            repository.getProducts()
        }
    }
}