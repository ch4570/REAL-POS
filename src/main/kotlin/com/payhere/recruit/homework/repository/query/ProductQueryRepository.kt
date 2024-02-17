package com.payhere.recruit.homework.repository.query

import com.payhere.recruit.homework.common.dto.response.ProductDetailResponse
import com.payhere.recruit.homework.common.dto.response.ProductResponse
import java.util.*

/**
 * Repository interface for executing various queries related to products.
 */
interface ProductQueryRepository {

    /**
     * Retrieves detailed information about a product based on the provided product ID.
     *
     * @param productId The ID of the product to retrieve details for.
     * @return An Optional containing the ProductDetailResponse if found, or empty if not found.
     */
    fun loadProductDetail(productId: Long): Optional<ProductDetailResponse>

    /**
     * Retrieves a list of products based on a text keyword search.
     *
     * @param prevProductId The ID of the last product in the previous page.
     * @param searchKeyword The keyword to search for in product names.
     * @return A list of ProductResponse objects matching the search criteria.
     */
    fun loadProductListByTextKeyword(prevProductId: Long?, searchKeyword: String?): List<ProductResponse>

    /**
     * Retrieves a list of products based on an initial keyword search.
     *
     * @param prevProductId The ID of the last product in the previous page.
     * @param searchKeyword The initial letters to search for in product names.
     * @return A list of ProductResponse objects matching the search criteria.
     */
    fun loadProductListByInitialKeyword(prevProductId: Long?, searchKeyword: String?): List<ProductResponse>
}