package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.dto.response.ProductResponse
import com.payhere.recruit.homework.repository.query.ProductQueryRepository
import com.payhere.recruit.homework.service.LoadProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils

/**
 * Service class for loading products.
 *
 * @property productQueryRepository The ProductQueryRepository instance for querying product data.
 */
@Service
@Transactional(readOnly = true)
class LoadProductService(
    private val productQueryRepository: ProductQueryRepository
) : LoadProductUseCase {

    /**
     * Loads details of a product by its ID.
     *
     * @param productId The ID of the product to load.
     * @return The optional containing the product details if found, otherwise empty.
     */
    override fun loadProductDetail(productId: Long) =
        productQueryRepository.loadProductDetail(productId)


    /**
     * Loads a list of products based on optional parameters.
     *
     * @param prevProductId The ID of the last product in the previous page, or null if not applicable.
     * @param searchKeyword The keyword to search for in product names, or null if not applicable.
     * @return The list of products.
     */
    override fun loadProductList(prevProductId: Long?, searchKeyword: String?) : List<ProductResponse> {
        if (isInitial(searchKeyword)) return productQueryRepository.loadProductListByInitialKeyword(prevProductId, searchKeyword)

        return productQueryRepository.loadProductListByTextKeyword(prevProductId, searchKeyword)
    }


    /**
     * Checks if the search keyword is an initial keyword.
     *
     * @param searchKeyword The search keyword to check.
     * @return true if the search keyword is an initial keyword, otherwise false.
     */
    private fun isInitial(searchKeyword: String?) : Boolean {
        if (StringUtils.hasText(searchKeyword)) {
            val unicode = searchKeyword!![0].code - 44032
            val initialIndex = unicode / 28 / 21
            println("결과 -> ${initialIndex in 0..18}")
            return true
        }

        return false
    }
}