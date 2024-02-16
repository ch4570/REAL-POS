package com.payletter.recruit.homework.repository

import com.payletter.recruit.homework.domain.entity.CategoryJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryJpaEntity, Long> {
}