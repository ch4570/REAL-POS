package com.payhere.recruit.homework.product.service

/**
 * 제품을 삭제하는 데 사용되는 유스케이스 인터페이스입니다.
 */
interface RemoveProductUseCase {

    /**
     * 주어진 ID를 가진 제품을 삭제합니다.
     *
     * @param productId 삭제할 제품의 ID입니다.
     */
    fun removeProduct(productId: Long)
}
