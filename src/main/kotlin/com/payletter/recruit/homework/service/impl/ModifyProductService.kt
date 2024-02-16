package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.common.dto.request.ModifyProductCommand
import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.repository.ProductRepository
import com.payletter.recruit.homework.service.ModifyProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyProductService(
    private val productRepository: ProductRepository
) : ModifyProductUseCase {
    override fun modifyProduct(productId: Long, modifyProductCommand: ModifyProductCommand) {
        val findProduct = productRepository.findById(productId)
            .orElseThrow { CustomException(NOT_EXISTS_PRODUCT) }

        findProduct.modifyProduct(modifyProductCommand)
    }


}