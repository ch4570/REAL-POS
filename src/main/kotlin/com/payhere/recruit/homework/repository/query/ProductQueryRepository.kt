package com.payhere.recruit.homework.repository.query

import com.payhere.recruit.homework.common.dto.response.ProductDetailResponse
import com.payhere.recruit.homework.common.dto.response.ProductResponse
import java.util.*

interface ProductQueryRepository {

    fun loadProductDetail(productId: Long) : Optional<ProductDetailResponse>
    fun loadProductListByTextKeyword(prevProductId: Long?, searchKeyword: String?) : List<ProductResponse>
    fun loadProductListByInitialKeyword(prevProductId: Long?, searchKeyword: String?) : List<ProductResponse>
}