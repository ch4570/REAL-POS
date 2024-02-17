package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.CreateProductCommand

interface CreateProductUseCase {

    fun createProduct(command: CreateProductCommand) : Long
}