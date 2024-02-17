package com.payhere.recruit.homework.repository

import com.payhere.recruit.homework.domain.entity.CategoryJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository interface for managing categories.
 */
interface CategoryRepository : JpaRepository<CategoryJpaEntity, Long>