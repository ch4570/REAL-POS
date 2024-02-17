package com.payhere.recruit.homework.repository

import com.payhere.recruit.homework.domain.entity.ProductJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductJpaEntity, Long> {

    fun existsByProductName(productName: String) : Boolean
}