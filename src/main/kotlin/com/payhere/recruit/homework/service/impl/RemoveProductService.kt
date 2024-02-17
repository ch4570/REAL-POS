package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.repository.ProductRepository
import com.payhere.recruit.homework.service.RemoveProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service class for removing products.
 *
 * @property productRepository The ProductRepository instance for managing product entities.
 */
@Service
@Transactional
class RemoveProductService(
    private val productRepository: ProductRepository
) : RemoveProductUseCase {

    /**
     * Removes a product based on its ID.
     *
     * @param productId The ID of the product to remove.
     * @throws CustomException if the product does not exist.
     */
    override fun removeProduct(productId: Long) {
        val findProduct = productRepository.findById(productId)
            .orElseThrow { CustomException(NOT_EXISTS_PRODUCT) }

        productRepository.delete(findProduct)
    }
}