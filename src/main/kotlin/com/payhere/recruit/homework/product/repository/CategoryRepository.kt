package com.payhere.recruit.homework.product.repository

import com.payhere.recruit.homework.product.domain.entity.CategoryJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * 카테고리를 관리하는 데 사용되는 Repository 인터페이스입니다.
 */
interface CategoryRepository : JpaRepository<CategoryJpaEntity, Long>