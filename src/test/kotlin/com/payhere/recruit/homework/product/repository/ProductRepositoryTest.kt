package com.payhere.recruit.homework.product.repository

import com.appmattus.kotlinfixture.kotlinFixture
import com.payhere.recruit.homework.product.domain.entity.ProductJpaEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class ProductRepositoryTest(
    private val productRepository: ProductRepository
) : BehaviorSpec({

    val fixture = kotlinFixture()

    Given("제품 존재 여부를 확인하려고 하는 상황에서") {
        val productName = "슈크림 붕어빵"
        val productEntity = fixture<ProductJpaEntity> {
            property<ProductJpaEntity, String>("productName") { productName }
        }

        productRepository.save(productEntity)

        When("상품의 이름으로 확인을 시도하면") {
            val actualResult = productRepository.existsByProductName(productName)

            Then("정상적으로 확인이 되어야 한다") {
                actualResult shouldBe true
            }
        }
    }
})