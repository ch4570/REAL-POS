package com.payhere.recruit.homework.product.repository

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.common.config.JpaConfig
import com.payhere.recruit.homework.product.domain.entity.CategoryJpaEntity
import com.payhere.recruit.homework.product.domain.entity.ProductJpaEntity
import com.payhere.recruit.homework.product.domain.entity.ProductSearchJpaEntity
import com.payhere.recruit.homework.product.repository.query.ProductQueryRepositoryImpl
import com.querydsl.jpa.impl.JPAQueryFactory
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(JpaConfig::class, Fixture::class)
internal class ProductQueryRepositoryTest(
    private val jpaQueryFactory: JPAQueryFactory,
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val productSearchRepository: ProductSearchRepository,
    private val fixture: Fixture
) : BehaviorSpec({

    val productQueryRepository = ProductQueryRepositoryImpl(jpaQueryFactory)

    Given("등록된 제품을 상세 조회하려는 상황에서") {

        val categoryEntity = fixture<CategoryJpaEntity>()
        val savedCategory = categoryRepository.save(categoryEntity)

        val productEntity = fixture<ProductJpaEntity> {
            property<ProductJpaEntity, Long>("categoryId") { savedCategory.categoryId!! }
        }

        val savedProduct = productRepository.save(productEntity)

        When("제품의 PK로 상세 조회를 시도하면") {
            val actualResult =
                productQueryRepository.loadProductDetail(savedProduct.productId!!).get()

            Then("정상적으로 조회가 되어야 한다") {
                actualResult.productId shouldBe productEntity.productId
                actualResult.cost shouldBe productEntity.cost
                actualResult.productName shouldBe  productEntity.productName
            }
        }
    }

    Given("등록되지 않은 제품을 상세 조회 하려는 상황에서") {

        val productEntity = fixture<ProductJpaEntity> {
            property<ProductJpaEntity, Long?>("categoryId") { -1293 }
        }

        val savedProduct = productRepository.save(productEntity)

        When("제품의 PK로 상세 조회를 시도하면") {
            val actualResult =
                productQueryRepository.loadProductDetail(savedProduct.productId!!)

            Then("정상적으로 조회가 되어야 한다") {
                actualResult.isPresent shouldBe false
            }
        }
    }

    Given("상품 리스트를 조회하려는 상황에서") {
        val productEntityList = List(20) { fixture<ProductJpaEntity>() }
        productRepository.saveAll(productEntityList)

        When("현재 페이지의 마지막 상품 번호를 서버에 전송하면") {
            val findProductList = productQueryRepository.loadProductListByTextKeyword(3, null)

            Then("마지막 상품 번호부터 10개의 데이터가 전송되어야 한다") {
                findProductList[0].productId shouldBe 4
                findProductList[9].productId shouldBe 13
                findProductList.size shouldBe 10
            }
        }
    }

    Given("상품 리스트를 초성 키워드로 검색하려는 상황에서") {
        val productName = "슈크림 라떼"
        val productEntity = fixture<ProductJpaEntity> {
            property<ProductJpaEntity, String>("productName") { productName }

        }
        val savedProductEntity = productRepository.save(productEntity)

        val productSearchEntity = fixture<ProductSearchJpaEntity> {
            property<ProductSearchJpaEntity, String>("productSearchKeyword") { "ㅅㅋㄹ ㄹㄸ" }
            property<ProductSearchJpaEntity, Long>("productId") { savedProductEntity.productId!! }
        }

        productSearchRepository.save(productSearchEntity)
        When("초성 키워드로 검색을 시도하면") {
            val firstKeyword = "ㅅㅋㄹ"
            val secondKeyword = "ㅅㅋㄹ ㄹ"
            val thirdKeyword = "ㄹㄸ"
            val forthKeyword = "ㅅㅋㄹ ㄹㄸ"
            val invalidKeyword = "ㅅㅋㄹ ㄹㄷ"

            val actualFirstResult = productQueryRepository.loadProductListByInitialKeyword(null, firstKeyword)
            val actualSecondResult = productQueryRepository.loadProductListByInitialKeyword(null, secondKeyword)
            val actualThirdResult = productQueryRepository.loadProductListByInitialKeyword(null, thirdKeyword)
            val actualForthResult = productQueryRepository.loadProductListByInitialKeyword(null, forthKeyword)
            val invalidResult = productQueryRepository.loadProductListByInitialKeyword(null, invalidKeyword)

            Then("정상적으로 조회가 되어야 한다") {
                actualFirstResult[0].productName shouldBe productName
                actualSecondResult[0].productName shouldBe productName
                actualThirdResult[0].productName shouldBe productName
                actualForthResult[0].productName shouldBe productName
                invalidResult.size shouldBe 0
            }
        }
    }

    Given("상품 리스트를 일반 키워드로 조회 하려는 상황에서") {
        val productName = "이탈리안 봉골레 스파게티"
        val productEntity = fixture<ProductJpaEntity> {
            property<ProductJpaEntity, String>("productName") { productName }

        }
        productRepository.save(productEntity)

        When("일반 키워드로 조회를 시도하면") {
            val firstKeyword = "이탈리"
            val secondKeyword = "안 봉골"
            val thirdKeyword = "이탈리안 봉골레"
            val forthKeyword = "이탈리안 봉골레 스파게티"
            val invalidKeyword = "이턀리안 봉골레"

            val actualFirstResult = productQueryRepository.loadProductListByTextKeyword(null, firstKeyword)
            val actualSecondResult = productQueryRepository.loadProductListByTextKeyword(null, secondKeyword)
            val actualThirdResult = productQueryRepository.loadProductListByTextKeyword(null, thirdKeyword)
            val actualForthResult = productQueryRepository.loadProductListByTextKeyword(null, forthKeyword)
            val invalidResult = productQueryRepository.loadProductListByTextKeyword(null, invalidKeyword)

            Then("정상적으로 조회가 되어야 한다") {
                actualFirstResult[0].productName shouldBe productName
                actualSecondResult[0].productName shouldBe productName
                actualThirdResult[0].productName shouldBe productName
                actualForthResult[0].productName shouldBe productName
                invalidResult.size shouldBe 0
            }
        }
    }
})