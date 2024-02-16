package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.repository.ProductRepository
import com.payletter.recruit.homework.service.RemoveProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RemoveProductService(
    private val productRepository: ProductRepository
) : RemoveProductUseCase {
    override fun removeProduct(productId: Long) {
        val findProduct = productRepository.findById(productId)
            .orElseThrow { CustomException(NOT_EXISTS_PRODUCT) }

        productRepository.delete(findProduct)
    }
}