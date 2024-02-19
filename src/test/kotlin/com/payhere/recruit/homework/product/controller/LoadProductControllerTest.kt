package com.payhere.recruit.homework.product.controller

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.common.exception.GlobalCatcher
import com.payhere.recruit.homework.product.domain.dto.response.ProductDetailResponse
import com.payhere.recruit.homework.product.domain.dto.response.ProductResponse
import com.payhere.recruit.homework.product.service.LoadProductUseCase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.Optional

@SpringBootTest
@Import(Fixture::class)
internal class LoadProductControllerTest(
    private val fixture: Fixture
) : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val loadProductUseCase = mockk<LoadProductUseCase>()
    lateinit var mockMvc: MockMvc

    Given("특정 상품을 조회(정상 케이스) 요청을 하는 상황에서") {
        val expectedResult = fixture<ProductDetailResponse>()

        every { loadProductUseCase.loadProductDetail(any()) } returns Optional.of(expectedResult)

        mockMvc = MockMvcBuilders.standaloneSetup(
            LoadProductController(loadProductUseCase)
        ).setControllerAdvice(GlobalCatcher())
        .build()

        When("존재하는 상품의 PK로 조회 요청을 보내면") {
            val resultActions = mockMvc.perform(
                get("/api/products/1")
                    .contentType(APPLICATION_JSON))
                .andDo(print())

            Then("요청이 정상적으로 처리되어야 한다") {

                resultActions
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.data.productId").value(expectedResult.productId))
                    .andExpect(jsonPath("$.data.categoryName").value(expectedResult.categoryName))
                    .andExpect(jsonPath("$.data.cost").value(expectedResult.cost))
                    .andExpect(jsonPath("$.data.price").value(expectedResult.price))
                    .andExpect(jsonPath("$.data.productName").value(expectedResult.productName))
                    .andExpect(jsonPath("$.data.description").value(expectedResult.description))
                    .andExpect(jsonPath("$.data.barcode").value(expectedResult.barcode))
                    .andExpect(jsonPath("$.data.expirationDate").exists())
                    .andExpect(jsonPath("$.data.productSize").value(expectedResult.productSize.toString()))
                    .andExpect(jsonPath("$.data.regDate").exists())
                    .andExpect(jsonPath("$.data.modDate").exists())

                verify(exactly = 1) { loadProductUseCase.loadProductDetail(any()) }
            }
        }
    }

    Given("특정 상품을 조회(비정상 케이스) 요청을 하는 상황에서") {
        every { loadProductUseCase.loadProductDetail(any()) } returns Optional.empty()

        mockMvc = MockMvcBuilders.standaloneSetup(
            LoadProductController(loadProductUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("존재하지 않는 상품의 PK로 조회 요청을 보내면") {
            val resultActions = mockMvc.perform(
                get("/api/products/1")
                    .contentType(APPLICATION_JSON))
                .andDo(print())

            Then("204 No Content 응답이 와야 한다") {

                resultActions
                    .andExpect(status().isNoContent)

                verify(exactly = 1) { loadProductUseCase.loadProductDetail(any()) }
            }
        }
    }

    Given("등록된 상품 리스트 조회 요청을 하는 상황에서") {
        val expectedResultList = listOf(
            fixture<ProductResponse> {
                property<ProductResponse, Long>("productId") { 1L }
                property<ProductResponse, Int>("price") { 3000 }
                property<ProductResponse, String>("productName") { "슈크림 라떼" }
            },
            fixture<ProductResponse> {
                property<ProductResponse, Long>("productId") { 2L }
                property<ProductResponse, Int>("price") { 5000 }
                property<ProductResponse, String>("productName") { "디카페인 콜드브루" }
            }
        )

        every { loadProductUseCase.loadProductList(any(), any()) } returns expectedResultList

        mockMvc = MockMvcBuilders.standaloneSetup(
            LoadProductController(loadProductUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("상품 조회 요청을 보내면") {
            val resultActions = mockMvc.perform(
                get("/api/products")
                    .contentType(APPLICATION_JSON))
                .andDo(print())

            Then("요청이 정상적으로 처리되어야 한다") {
                resultActions
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.data[0].productId").value(1L))
                    .andExpect(jsonPath("$.data[0].price").value(3000))
                    .andExpect(jsonPath("$.data[0].productName").value("슈크림 라떼"))
                    .andExpect(jsonPath("$.data[0].regDate").exists())
                    .andExpect(jsonPath("$.data[0].modDate").exists())
                    .andExpect(jsonPath("$.data[1].productId").value(2L))
                    .andExpect(jsonPath("$.data[1].price").value(5000))
                    .andExpect(jsonPath("$.data[1].productName").value("디카페인 콜드브루"))
                    .andExpect(jsonPath("$.data[1].regDate").exists())
                    .andExpect(jsonPath("$.data[1].modDate").exists())

                verify(exactly = 1) { loadProductUseCase.loadProductList(any(), any()) }
            }
        }


    }
})