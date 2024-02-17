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

@Repository
class ProductQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ProductQueryRepository {
    override fun loadProductDetail(productId: Long) : Optional<ProductDetailResponse> {
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

    override fun loadProductListByTextKeyword(prevProductId: Long?, searchKeyword: String?) :
            List<ProductResponse> =
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

    override fun loadProductListByInitialKeyword(prevProductId: Long?, searchKeyword: String?) : List<ProductResponse> {
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


    private fun categoryIdEq() : BooleanExpression =
        productJpaEntity.categoryId.eq(categoryJpaEntity.categoryId)

    private fun productTextNameLike(textSearchKeyword: String?) : BooleanExpression? =
        if (StringUtils.hasText(textSearchKeyword))
            productJpaEntity.productName.contains(textSearchKeyword)
        else null

    private fun productInitialNameLike(initialSearchKeyword: String?) : BooleanExpression? =
        if (StringUtils.hasText(initialSearchKeyword))
            productSearchJpaEntity.productSearchKeyword.contains(initialSearchKeyword)
        else null

    private fun productIdGt(prevProductId: Long?) : BooleanExpression? =
        if (Objects.nonNull(prevProductId))
            productJpaEntity.productId.gt(prevProductId)
        else null
}