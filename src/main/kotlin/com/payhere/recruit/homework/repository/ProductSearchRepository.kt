package com.payhere.recruit.homework.repository

import com.payhere.recruit.homework.domain.entity.ProductSearchJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository interface for managing product searches.
 */
interface ProductSearchRepository : JpaRepository<ProductSearchJpaEntity, Long>