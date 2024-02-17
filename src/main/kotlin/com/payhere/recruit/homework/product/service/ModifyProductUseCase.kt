package com.payhere.recruit.homework.product.service

import com.payhere.recruit.homework.product.domain.dto.request.ModifyProductCommand

/**
 * 제품을 수정하는 데 사용되는 유스케이스 인터페이스입니다.
 */
interface ModifyProductUseCase {
    /**
     * 제공된 명령을 기반으로 제품을 수정합니다.
     *
     * @param productId 수정할 제품의 ID입니다.
     * @param modifyProductCommand 제품을 수정하는 데 필요한 데이터가 포함된 명령입니다.
     */
    fun modifyProduct(productId: Long, modifyProductCommand: ModifyProductCommand)
}
