package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.ModifyProductCommand

/**
 * Use case interface for modifying products.
 */
interface ModifyProductUseCase {
    /**
     * Modifies a product based on the provided command.
     *
     * @param productId The ID of the product to modify.
     * @param modifyProductCommand The command containing data for modifying the product.
     */
    fun modifyProduct(productId: Long, modifyProductCommand: ModifyProductCommand)
}