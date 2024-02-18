package com.payhere.recruit.homework.product.usecase

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.product.domain.entity.ProductSearchJpaEntity
import com.payhere.recruit.homework.product.repository.ProductSearchRepository
import com.payhere.recruit.homework.product.service.impl.CreateProductSearchService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.springframework.context.annotation.Import

@Import(Fixture::class)
internal class CreateProductSearchUseCaseTest(
    private val fixture: Fixture
) : BehaviorSpec({

    val productSearchRepository = mockk<ProductSearchRepository>()
    val createProductSearchUseCase = CreateProductSearchService(productSearchRepository)

    Given("상품 키워드 검색용 데이터가 전송된 상태에서") {
        val productSearchId = 1L
        val productId = 3L
        val productSearchKeyword = "ㅅㅋㄹ ㄹㄸ"

        val productSearchEntity = fixture<ProductSearchJpaEntity> {
            property<ProductSearchJpaEntity, Long?>("productSearchId") { productSearchId }
            property<ProductSearchJpaEntity, Long>("productId") { productId }
            property<ProductSearchJpaEntity, String>("productSearchKeyword") { productSearchKeyword }
        }

        every { productSearchRepository.save(any()) } returns productSearchEntity

        When("데이터를 저장하려고 하면") {
            val actualResult = createProductSearchUseCase.createProductSearch(productId, productSearchKeyword)

            Then("데이터가 정상적으로 저장되어야 한다") {
                actualResult shouldBe productSearchId
                verify(exactly = 1) { productSearchRepository.save(any()) }
            }
        }
    }
})