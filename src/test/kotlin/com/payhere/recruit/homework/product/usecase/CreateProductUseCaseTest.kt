package com.payhere.recruit.homework.product.usecase

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.product.domain.dto.request.CreateProductCommand
import com.payhere.recruit.homework.product.domain.entity.ProductJpaEntity
import com.payhere.recruit.homework.product.repository.ProductRepository
import com.payhere.recruit.homework.product.service.CreateProductSearchUseCase
import com.payhere.recruit.homework.product.service.impl.CreateProductService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.annotation.Import

@Import(Fixture::class)
internal class CreateProductUseCaseTest(
    fixture: Fixture
) : BehaviorSpec({

    val productRepository = mockk<ProductRepository>()
    val createProductSearchUseCase = mockk<CreateProductSearchUseCase>(relaxed = true)
    val createProductUseCase = CreateProductService(productRepository, createProductSearchUseCase)

    Given("중복되지 않은 상품 등록을 위한 데이터가 전송된 상태에서") {
        val productId = 1L

        val createProductCommand = fixture<CreateProductCommand> {
            property<CreateProductCommand, String>("expirationDate") { "2023-11-23 23:12:35" }
        }
        val productEntity = fixture<ProductJpaEntity> {
            property<ProductJpaEntity, Long?>("productId") { productId }
        }

        every { productRepository.save(any()) } returns productEntity
        every { productRepository.existsByProductName(any()) } returns false

        When("데이터를 저장하려고 하면") {
            val actualResult = createProductUseCase.createProduct(createProductCommand)

            Then("데이터가 정상적으로 저장되어야 한다") {
                actualResult shouldBe productId
                verify(exactly = 1) { productRepository.save(any()) }
            }
        }
    }

    Given("중복된 상품 등록을 위한 데이터가 전송된 상태에서") {
        val productId = 1L

        val createProductCommand = fixture<CreateProductCommand> {
            property<CreateProductCommand, String>("expirationDate") { "2023-11-23 23:12:35" }
        }
        val productEntity = fixture<ProductJpaEntity> {
            property<ProductJpaEntity, Long?>("productId") { productId }
        }

        every { productRepository.save(any()) } returns productEntity
        every { productRepository.existsByProductName(any()) } returns true

        When("데이터를 저장하려고 하면") {
            val exception = shouldThrow<CustomException> {
                createProductUseCase.createProduct(createProductCommand)
            }

            Then("데이터가 정상적으로 저장되어야 한다") {
                exception.errorCode shouldBe IS_ALREADY_EXISTS_PRODUCT
                verify(exactly = 1) { productRepository.save(any()) }
            }
        }
    }
})