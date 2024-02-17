package com.payhere.recruit.homework.repository

import com.payhere.recruit.homework.domain.entity.ProductJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository interface for managing products.
 */
interface ProductRepository : JpaRepository<ProductJpaEntity, Long> {

    /**
     * Checks if a product exists with the given product name.
     *
     * @param productName The name of the product to check for existence.
     * @return true if a product with the given name exists, otherwise false.
     */
    fun existsByProductName(productName: String): Boolean
}