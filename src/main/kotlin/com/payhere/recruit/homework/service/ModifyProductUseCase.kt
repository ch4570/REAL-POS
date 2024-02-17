package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.ModifyProductCommand

interface ModifyProductUseCase {
    fun modifyProduct(productId: Long, modifyProductCommand: ModifyProductCommand)
}