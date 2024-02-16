package com.payletter.recruit.homework.service

import com.payletter.recruit.homework.common.dto.request.CreateProductCommand

interface CreateProductUseCase {

    fun createProduct(command: CreateProductCommand) : Long
}