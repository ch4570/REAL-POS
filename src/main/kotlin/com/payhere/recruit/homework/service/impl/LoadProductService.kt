package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.dto.response.ProductResponse
import com.payhere.recruit.homework.repository.query.ProductQueryRepository
import com.payhere.recruit.homework.service.LoadProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils

@Service
@Transactional(readOnly = true)
class LoadProductService(
    private val productQueryRepository: ProductQueryRepository
) : LoadProductUseCase {
    override fun loadProductDetail(productId: Long) =
        productQueryRepository.loadProductDetail(productId)

    override fun loadProductList(prevProductId: Long?, searchKeyword: String?) : List<ProductResponse> {
        if (isInitial(searchKeyword)) return productQueryRepository.loadProductListByInitialKeyword(prevProductId, searchKeyword)

        return productQueryRepository.loadProductListByTextKeyword(prevProductId, searchKeyword)
    }



    private fun isInitial(searchKeyword: String?) : Boolean {
        if (StringUtils.hasText(searchKeyword)) {
            val unicode = searchKeyword!![0].code - 44032
            val initialIndex = unicode / 28 / 21
            println("결과 -> ${initialIndex in 0..18}")
            return true
        }

        return false
    }


}