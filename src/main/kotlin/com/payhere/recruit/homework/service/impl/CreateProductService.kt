package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.dto.request.CreateProductCommand
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.repository.ProductRepository
import com.payhere.recruit.homework.service.CreateProductSearchUseCase
import com.payhere.recruit.homework.service.CreateProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


/**
 * Service class for creating products.
 *
 * @property productRepository The ProductRepository instance for managing product entities.
 * @property createProductSearchUseCase The CreateProductSearchUseCase instance for creating product searches.
 */
@Service
@Transactional
class CreateProductService(
    private val productRepository: ProductRepository,
    private val createProductSearchUseCase: CreateProductSearchUseCase
) : CreateProductUseCase {

    /**
     * Creates a new product based on the provided command.
     *
     * @param command The command containing data for creating the product.
     * @return The ID of the created product.
     * @throws CustomException if a product with the same name already exists.
     */
    override fun createProduct(command: CreateProductCommand): Long {
        val productEntity = command.mapToJpaEntity()

        val isDuplicated = productRepository.existsByProductName(productEntity.productName)
        if (isDuplicated) throw CustomException(IS_ALREADY_EXISTS_PRODUCT)

        productRepository.save(productEntity)
        createProductSearchUseCase.createProductSearch(
            productId = productEntity.productId!!,
            productSearchKeyword = productEntity.productName
        )

        return productEntity.productId
    }
}