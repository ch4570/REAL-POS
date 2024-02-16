package com.payletter.recruit.homework.service

interface CreateProductSearchUseCase {

    fun createProductSearch(productId: Long, productSearchKeyword: String)
}