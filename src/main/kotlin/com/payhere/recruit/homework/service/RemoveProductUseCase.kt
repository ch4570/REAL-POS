package com.payhere.recruit.homework.service

/**
 * Use case interface for removing products.
 */
interface RemoveProductUseCase {

    /**
     * Removes a product with the given ID.
     *
     * @param productId The ID of the product to remove.
     */
    fun removeProduct(productId: Long)
}