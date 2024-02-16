package com.payletter.recruit.homework.service

import com.payletter.recruit.homework.common.dto.response.ProductDetailResponse
import com.payletter.recruit.homework.common.dto.response.ProductResponse
import java.util.Optional

interface LoadProductUseCase {

    fun loadProductDetail(productId: Long) : Optional<ProductDetailResponse>
    fun loadProductList(prevProductId: Long?, searchKeyword: String?) : List<ProductResponse>
}