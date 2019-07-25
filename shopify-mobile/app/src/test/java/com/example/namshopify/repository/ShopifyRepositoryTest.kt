package com.example.namshopify.repository

import com.example.namshopify.api.MyShopifyApi
import com.example.namshopify.data.ProductList
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Call
import retrofit2.Response


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class ShopifyRepositoryTest {

    @MockK
    lateinit var myShopifyApi: MyShopifyApi
    @MockK
    lateinit var callResult: Call<ProductList>
    @MockK
    lateinit var responseMock: Response<ProductList>
    lateinit var repository : ShopifyRepository

    @Test
    fun testRepositoryCall() {
        every {
            myShopifyApi.getProducts(any(), any())
        } returns callResult
        every {
            callResult.execute()
        } returns responseMock
        every {
            responseMock.body()
        } returns ProductList(listOf())

        repository = ShopifyRepository(myShopifyApi)
        repository.getProducts()
        verify { myShopifyApi.getProducts(any(), any()) }
        verify { callResult.execute() }
        verify { responseMock.body() }
    }

    @Test
    fun testStaticInstance() {
        repository = ShopifyRepository.create()
        assertNotNull(repository)
    }
}