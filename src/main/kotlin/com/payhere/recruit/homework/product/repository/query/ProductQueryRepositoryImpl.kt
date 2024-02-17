package com.payhere.recruit.homework.product.repository.query

import com.payhere.recruit.homework.product.domain.dto.response.ProductDetailResponse
import com.payhere.recruit.homework.product.domain.dto.response.ProductResponse
import com.payhere.recruit.homework.product.domain.entity.QCategoryJpaEntity.categoryJpaEntity
import com.payhere.recruit.homework.product.domain.entity.QProductJpaEntity.productJpaEntity
import com.payhere.recruit.homework.product.domain.entity.QProductSearchJpaEntity.productSearchJpaEntity

import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.util.*

/**
 * 제품과 관련된 다양한 쿼리를 실행하는 데 사용되는 ProductQueryRepository 인터페이스의 구현체입니다.
 *
 * @property jpaQueryFactory JPA 쿼리를 생성하는 데 사용되는 JPAQueryFactory입니다.
 */
@Repository
class ProductQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ProductQueryRepository {

    private val pageLimit = 10L

    /**
     * 제품 ID를 기반으로 제품에 대한 세부 정보를 검색합니다.
     *
     * @param productId 세부 정보를 검색할 제품의 ID입니다.
     * @return 제품 세부 정보가 포함된 Optional입니다. 제품을 찾지 못한 경우 비어 있습니다.
     */
    override fun loadProductDetail(productId: Long): Optional<ProductDetailResponse> {
        val findProduct = jpaQueryFactory.select(
            Projections.constructor(
                ProductDetailResponse::class.java,
                productJpaEntity.productId,
                categoryJpaEntity.categoryName,
                productJpaEntity.cost,
                productJpaEntity.price,
                productJpaEntity.productName,
                productJpaEntity.description,
                productJpaEntity.barcode,
                productJpaEntity.expirationDate,
                productJpaEntity.productSize,
                productJpaEntity.regDate,
                productJpaEntity.modDate)
        ).from(productJpaEntity)
            .join(categoryJpaEntity).on(categoryIdEq())
            .where(productJpaEntity.productId.eq(productId))
            .fetchOne()

        return Optional.ofNullable(findProduct)
    }

    /**
     * 텍스트 키워드 검색을 기반으로 제품 목록을 검색합니다.
     *
     * @param prevProductId 이전 페이지의 마지막 제품 ID입니다.
     * @param searchKeyword 제품 이름에서 검색할 키워드입니다.
     * @return 검색 기준과 일치하는 ProductResponse 객체 목록입니다.
     */
    override fun loadProductListByTextKeyword(prevProductId: Long?, searchKeyword: String?): List<ProductResponse> =
        jpaQueryFactory.select(
            Projections.constructor(
                ProductResponse::class.java,
                productJpaEntity.productId,
                productJpaEntity.price,
                productJpaEntity.productName,
                productJpaEntity.regDate,
                productJpaEntity.modDate)
        ).from(productJpaEntity)
            .where(productTextNameLike(searchKeyword),
                productIdGt(prevProductId)
            ).limit(pageLimit)
            .fetch()

    /**
     * 초기 키워드 검색을 기반으로 제품 목록을 검색합니다.
     *
     * @param prevProductId 이전 페이지의 마지막 제품 ID입니다.
     * @param searchKeyword 제품 이름에서 초성 글자를 검색할 키워드입니다.
     * @return 검색 기준과 일치하는 ProductResponse 객체 목록입니다.
     */
    override fun loadProductListByInitialKeyword(prevProductId: Long?, searchKeyword: String?): List<ProductResponse> {
        return jpaQueryFactory.select(
            Projections.constructor(
                ProductResponse::class.java,
                productJpaEntity.productId,
                productJpaEntity.price,
                productJpaEntity.productName,
                productJpaEntity.regDate,
                productJpaEntity.modDate)
        ).from(productJpaEntity)
            .join(productSearchJpaEntity).on(productJpaEntity.productId.eq(productSearchJpaEntity.productId))
            .where(productInitialNameLike(searchKeyword), productIdGt(prevProductId))
            .limit(pageLimit)
            .fetch()
    }

    /**
     * 제품 카테고리 ID가 동일한지 확인하는 BooleanExpression을 생성합니다.
     *
     * @return 동일성을 확인하는 BooleanExpression입니다.
     */
    private fun categoryIdEq(): BooleanExpression =
        productJpaEntity.categoryId.eq(categoryJpaEntity.categoryId)

    /**
     * 제품 이름에서 텍스트 검색 키워드를 필터링하는 BooleanExpression을 생성합니다.
     *
     * @param textSearchKeyword 제품 이름에서 검색할 키워드입니다.
     * @return 텍스트 검색 기준을 나타내는 BooleanExpression입니다.
     */
    private fun productTextNameLike(textSearchKeyword: String?): BooleanExpression? =
        if (!textSearchKeyword.isNullOrBlank())
            productJpaEntity.productName.contains(textSearchKeyword)
        else null

    /**
     * 제품 이름에서 초기 키워드 검색을 필터링하는 BooleanExpression을 생성합니다.
     *
     * @param initialSearchKeyword 제품 이름에서 초성 글자를 검색할 키워드입니다.
     * @return 초성 키워드 검색 기준을 나타내는 BooleanExpression입니다.
     */
    private fun productInitialNameLike(initialSearchKeyword: String?): BooleanExpression? =
        if (!initialSearchKeyword.isNullOrBlank())
            productSearchJpaEntity.productSearchKeyword.contains(initialSearchKeyword)
        else null

    /**
     * 주어진 값보다 큰 제품 ID를 기준으로 제품을 필터링하는 BooleanExpression을 생성합니다.
     *
     * @param prevProductId 이전 페이지의 마지막 제품 ID입니다.
     * @return 비교 기준을 나타내는 BooleanExpression입니다.
     */
    private fun productIdGt(prevProductId: Long?): BooleanExpression? =
        if (Objects.nonNull(prevProductId))
            productJpaEntity.productId.gt(prevProductId)
        else null
}
