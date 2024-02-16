package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.common.dto.request.CreateProductCommand
import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.repository.ProductRepository
import com.payletter.recruit.homework.service.CreateProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateProductService(
    private val productRepository: ProductRepository
) : CreateProductUseCase {
    override fun createProduct(createProductCommand: CreateProductCommand): Long {
        val productEntity = createProductCommand.mapToJpaEntity()

        val isDuplicated = productRepository.existsByProductName(productEntity.productName)
        if (isDuplicated) throw CustomException(IS_ALREADY_EXIST_PRODUCT)

        productRepository.save(productEntity)
        return productEntity.productId!!
    }
}