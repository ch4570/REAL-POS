package com.payletter.recruit.homework.repository

import com.payletter.recruit.homework.domain.entity.ProductJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductJpaEntity, Long> {

    fun existsByProductName(productName: String) : Boolean
}