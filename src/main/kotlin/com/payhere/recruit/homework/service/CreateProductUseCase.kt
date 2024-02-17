package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.CreateProductCommand

/**
 * Use case interface for creating products.
 */
interface CreateProductUseCase {

    /**
     * Creates a new product based on the provided command.
     *
     * @param command The command containing data for creating the product.
     * @return The ID of the created product.
     */
    fun createProduct(command: CreateProductCommand): Long
}