package com.payhere.recruit.homework.service

interface CreateProductSearchUseCase {

    fun createProductSearch(productId: Long, productSearchKeyword: String)
}