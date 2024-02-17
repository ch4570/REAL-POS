package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.dto.request.CreateProductCommand
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.repository.ProductRepository
import com.payhere.recruit.homework.service.CreateProductSearchUseCase
import com.payhere.recruit.homework.service.CreateProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateProductService(
    private val productRepository: ProductRepository,
    private val createProductSearchUseCase: CreateProductSearchUseCase
) : CreateProductUseCase {
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