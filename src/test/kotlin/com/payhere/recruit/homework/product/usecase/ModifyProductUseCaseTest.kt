package com.payhere.recruit.homework.product.usecase

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.common.util.LocalDateTimeConverter
import com.payhere.recruit.homework.product.domain.dto.request.ModifyProductCommand
import com.payhere.recruit.homework.product.domain.entity.ProductJpaEntity
import com.payhere.recruit.homework.product.repository.ProductRepository
import com.payhere.recruit.homework.product.service.impl.ModifyProductService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.annotation.Import
import java.util.*

@Import(Fixture::class)
internal class ModifyProductUseCaseTest(
    private val fixture: Fixture
) : BehaviorSpec({

    val productRepository = mockk<ProductRepository>()
    val modifyProductUseCase = ModifyProductService(productRepository)

    Given("등록된 상품의 정보를 수정하려는 상황에서") {
        val productName = "슈크림 붕어빵"

        val modifyProductCommand = fixture<ModifyProductCommand> {
            property<ModifyProductCommand, String>("productName") { productName }
        }

        val productEntity = fixture<ProductJpaEntity>()

        every { productRepository.findById(any()) } returns Optional.of(productEntity)

        When("상품 정보를 수정하려고 하면") {
            modifyProductUseCase.modifyProduct(1L, modifyProductCommand)

            Then("상품의 정보가 정상적으로 수정되어야 한다") {
                productEntity.productName shouldBe productName
                productEntity.expirationDate shouldBe modifyProductCommand.expirationDate
                verify(exactly = 1) { productRepository.findById(any()) }
            }
        }
    }
})