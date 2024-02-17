package com.payhere.recruit.homework.product.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.payhere.recruit.homework.product.domain.dto.response.ProductDetailResponse
import com.payhere.recruit.homework.product.domain.dto.response.ProductResponse
import com.payhere.recruit.homework.product.repository.query.ProductQueryRepository
import com.payhere.recruit.homework.product.service.impl.LoadProductService
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

internal class LoadProductUseCaseTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val productQueryRepository = mockk<ProductQueryRepository>()
    val loadProductUseCase = LoadProductService(productQueryRepository)
    val fixture = kotlinFixture()

    Given("존재하는 특정 제품의 세부 정보를 조회 하려고 하는 상황에서") {
        val productId = 1L
        val categoryName = "디저트"

        val productDetailResponse = fixture<ProductDetailResponse> {
            property<ProductDetailResponse, Long>("productId") { productId }
            property<ProductDetailResponse, String>("categoryName") { categoryName }
        }

        every { productQueryRepository.loadProductDetail(any()) } returns Optional.of(productDetailResponse)

        When("존재하지 제품의 PK로 조회를 시도하면") {
            val actualResult = loadProductUseCase.loadProductDetail(productId)

            Then("제품이 정상적으로 조회 되어야 한다") {
                actualResult.isPresent shouldBe true
                actualResult.get().productId shouldBe productId
                actualResult.get().categoryName shouldBe categoryName

                verify(exactly = 1) { productQueryRepository.loadProductDetail(any()) }
            }
        }
    }

    Given("존재하지 않는 제품의 세부 정보를 조회 하려고 하는 상황에서") {
        val productId = 1L
        every { productQueryRepository.loadProductDetail(any()) } returns Optional.empty()

        When("존재하지 않는 제품의 PK로 조회를 시도하면") {
            val actualResult = loadProductUseCase.loadProductDetail(productId)

            Then("제품이 정상적으로 조회 되어야 한다") {
                actualResult.isPresent shouldBe false
                verify(exactly = 1) { productQueryRepository.loadProductDetail(any()) }
            }
        }
    }

    Given("일반 검색 키워드로 제품 목록을 조회하려고 하는 상황에서") {
        val keyword = "슈크림 라떼"
        val productList = listOf(
            fixture<ProductResponse>(), fixture<ProductResponse>(),
            fixture<ProductResponse>(), fixture<ProductResponse>()
        )

        every { productQueryRepository.loadProductListByTextKeyword(any(), any())} returns productList

        When("일반 검색 키워드로 조회를 시도하면") {
            val actualResult = loadProductUseCase.loadProductList(null, keyword)

            Then("제품 리스트가 정상적으로 조회 되어야 한다") {
                actualResult.size shouldBe 4
                actualResult shouldBe productList
                verify(exactly = 1) { productQueryRepository.loadProductListByTextKeyword(any(), any()) }
            }
        }
    }

    Given("초성 검색 키워드로 제품 목록을 조회하려고 하는 상황에서") {
        val keyword = "ㅅㅋㄹ ㄹㄸ"
        val productList = listOf(
            fixture<ProductResponse>(), fixture<ProductResponse>(),
            fixture<ProductResponse>(), fixture<ProductResponse>()
        )

        every { productQueryRepository.loadProductListByInitialKeyword(any(), any())} returns productList

        When("초성 검색 키워드로 조회를 시도하면") {
            val actualResult = loadProductUseCase.loadProductList(null, keyword)

            Then("제품 리스트가 정상적으로 조회 되어야 한다") {
                actualResult.size shouldBe 4
                actualResult shouldBe productList
                verify(exactly = 1) { productQueryRepository.loadProductListByInitialKeyword(any(), any()) }
            }
        }
    }
})