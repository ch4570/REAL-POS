package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.dto.request.ModifyProductCommand
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.repository.ProductRepository
import com.payhere.recruit.homework.service.ModifyProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service class for modifying products.
 *
 * @property productRepository The ProductRepository instance for managing product entities.
 */
@Service
@Transactional
class ModifyProductService(
    private val productRepository: ProductRepository
) : ModifyProductUseCase {

    /**
     * Modifies a product based on the provided command.
     *
     * @param productId The ID of the product to modify.
     * @param modifyProductCommand The command containing data for modifying the product.
     * @throws CustomException if the product does not exist.
     */
    override fun modifyProduct(productId: Long, modifyProductCommand: ModifyProductCommand) {
        val findProduct = productRepository.findById(productId)
            .orElseThrow { CustomException(NOT_EXISTS_PRODUCT) }

        findProduct.modifyProduct(modifyProductCommand)
    }
}