package com.payhere.recruit.homework.service

/**
 * Use case interface for creating product searches.
 */
interface CreateProductSearchUseCase {

    /**
     * Creates a product search entry for the given product ID and search keyword.
     *
     * @param productId The ID of the product to associate with the search.
     * @param productSearchKeyword The search keyword to associate with the product.
     */
    fun createProductSearch(productId: Long, productSearchKeyword: String)
}