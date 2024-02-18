package com.payhere.recruit.homework.product.usecase

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.product.domain.entity.ProductJpaEntity
import com.payhere.recruit.homework.product.repository.ProductRepository
import com.payhere.recruit.homework.product.service.impl.RemoveProductService
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*
import org.springframework.context.annotation.Import
import java.util.*

@Import(Fixture::class)
internal class RemoveProductUseCaseTest(
    private val fixture: Fixture
) : BehaviorSpec({

    val productRepository = mockk<ProductRepository>()
    val removeProductService = RemoveProductService(productRepository)

    Given("등록된 상품을 삭제하려고 하는 상황에서") {
        val productId = 1L
        val productEntity = fixture<ProductJpaEntity> {
            property<ProductJpaEntity, Long?>("productId") { productId }
        }

        every { productRepository.findById(any()) } returns Optional.of(productEntity)
        every { productRepository.delete(any()) } just runs

        When("상품 삭제를 시도하면") {
            removeProductService.removeProduct(productId)

            Then("정상적으로 상품 삭제가 되어야 한다") {
                verify(exactly = 1) { productRepository.findById(any()) }
                verify(exactly = 1) { productRepository.delete(any()) }
            }
        }
    }
})