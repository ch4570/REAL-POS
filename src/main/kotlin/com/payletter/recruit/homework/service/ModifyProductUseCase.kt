package com.payletter.recruit.homework.service

import com.payletter.recruit.homework.common.dto.request.ModifyProductCommand

interface ModifyProductUseCase {
    fun modifyProduct(productId: Long, modifyProductCommand: ModifyProductCommand)
}