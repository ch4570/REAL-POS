package com.payhere.recruit.homework.product.repository

import com.payhere.recruit.homework.product.domain.entity.ProductSearchJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * 제품 검색을 관리하는 데 사용되는 Repository 인터페이스입니다.
 */
interface ProductSearchRepository : JpaRepository<ProductSearchJpaEntity, Long>
