package com.payhere.recruit.homework.repository.query

import com.payhere.recruit.homework.common.dto.response.ProductDetailResponse
import com.payhere.recruit.homework.common.dto.response.ProductResponse
import com.payhere.recruit.homework.domain.entity.QCategoryJpaEntity.*
import com.payhere.recruit.homework.domain.entity.QProductJpaEntity.*
import com.payhere.recruit.homework.domain.entity.QProductSearchJpaEntity.*
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import java.util.*

/**
 * Implementation of the ProductQueryRepository interface for executing various queries related to products.
 *
 * @property jpaQueryFactory The JPAQueryFactory used for creating JPA queries.
 */
@Repository
class ProductQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ProductQueryRepository {

    /**
     * Retrieves detailed information about a product based on the provided product ID.
     *
     * @param productId The ID of the product to retrieve details for.
     * @return An Optional containing the ProductDetailResponse if found, or empty if not found.
     */
    override fun loadProductDetail(productId: Long): Optional<ProductDetailResponse> {
        val findProduct = jpaQueryFactory.select(
            Projections.constructor(ProductDetailResponse::class.java,
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
     * Retrieves a list of products based on a text keyword search.
     *
     * @param prevProductId The ID of the last product in the previous page.
     * @param searchKeyword The keyword to search for in product names.
     * @return A list of ProductResponse objects matching the search criteria.
     */
    override fun loadProductListByTextKeyword(prevProductId: Long?, searchKeyword: String?): List<ProductResponse> =
        jpaQueryFactory.select(
            Projections.constructor(ProductResponse::class.java,
                productJpaEntity.productId,
                productJpaEntity.price,
                productJpaEntity.productName,
                productJpaEntity.regDate,
                productJpaEntity.modDate)
        ).from(productJpaEntity)
            .where(productTextNameLike(searchKeyword),
                productIdGt(prevProductId)
            ).limit(5)
            .fetch()

    /**
     * Retrieves a list of products based on an initial keyword search.
     *
     * @param prevProductId The ID of the last product in the previous page.
     * @param searchKeyword The initial letters to search for in product names.
     * @return A list of ProductResponse objects matching the search criteria.
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
            .limit(5)
            .fetch()
    }

    /**
     * Constructs a BooleanExpression for checking if product category IDs are equal.
     *
     * @return A BooleanExpression representing the equality check.
     */
    private fun categoryIdEq(): BooleanExpression =
        productJpaEntity.categoryId.eq(categoryJpaEntity.categoryId)

    /**
     * Constructs a BooleanExpression for filtering products based on a text search keyword.
     *
     * @param textSearchKeyword The keyword to search for in product names.
     * @return A BooleanExpression representing the text search criteria.
     */
    private fun productTextNameLike(textSearchKeyword: String?): BooleanExpression? =
        if (StringUtils.hasText(textSearchKeyword))
            productJpaEntity.productName.contains(textSearchKeyword)
        else null

    /**
     * Constructs a BooleanExpression for filtering products based on an initial keyword search.
     *
     * @param initialSearchKeyword The initial letters to search for in product names.
     * @return A BooleanExpression representing the initial keyword search criteria.
     */
    private fun productInitialNameLike(initialSearchKeyword: String?): BooleanExpression? =
        if (StringUtils.hasText(initialSearchKeyword))
            productSearchJpaEntity.productSearchKeyword.contains(initialSearchKeyword)
        else null

    /**
     * Constructs a BooleanExpression for filtering products based on product ID greater than a given value.
     *
     * @param prevProductId The ID of the last product in the previous page.
     * @return A BooleanExpression representing the comparison criteria.
     */
    private fun productIdGt(prevProductId: Long?): BooleanExpression? =
        if (Objects.nonNull(prevProductId))
            productJpaEntity.productId.gt(prevProductId)
        else null
}