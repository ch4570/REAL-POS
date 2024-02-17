package com.payhere.recruit.homework.product.service.impl

import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.product.repository.ProductRepository
import com.payhere.recruit.homework.product.service.RemoveProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 제품을 삭제하는 데 사용되는 서비스 클래스입니다.
 *
 * @property productRepository 제품 엔티티를 관리하는 ProductRepository 인스턴스입니다.
 */
@Service
@Transactional
class RemoveProductService(
    private val productRepository: ProductRepository
) : RemoveProductUseCase {

    /**
     * 제품의 ID를 기반으로 제품을 삭제합니다.
     *
     * @param productId 삭제할 제품의 ID입니다.
     * @throws CustomException 제품이 존재하지 않는 경우 예외가 발생합니다.
     */
    override fun removeProduct(productId: Long) {
        val findProduct = productRepository.findById(productId)
            .orElseThrow { CustomException(NOT_EXISTS_PRODUCT) }

        productRepository.delete(findProduct)
    }
}
