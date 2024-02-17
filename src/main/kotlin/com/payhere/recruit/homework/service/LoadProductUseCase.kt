package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.response.ProductDetailResponse
import com.payhere.recruit.homework.common.dto.response.ProductResponse
import java.util.Optional

/**
 * Use case interface for loading products.
 */
interface LoadProductUseCase {

    /**
     * Loads details of a product by its ID.
     *
     * @param productId The ID of the product to load.
     * @return An optional containing the product details if found, otherwise empty.
     */
    fun loadProductDetail(productId: Long): Optional<ProductDetailResponse>

    /**
     * Loads a list of products based on optional parameters.
     *
     * @param prevProductId The ID of the last product in the previous page, or null if not applicable.
     * @param searchKeyword The keyword to search for in product names, or null if not applicable.
     * @return The list of products.
     */
    fun loadProductList(prevProductId: Long?, searchKeyword: String?): List<ProductResponse>
}