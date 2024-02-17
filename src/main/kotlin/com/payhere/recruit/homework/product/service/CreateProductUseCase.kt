package com.payhere.recruit.homework.product.service

import com.payhere.recruit.homework.product.domain.dto.request.CreateProductCommand

/**
 * 제품을 생성하는 데 사용되는 유스케이스 인터페이스입니다.
 */
interface CreateProductUseCase {

    /**
     * 제공된 명령을 기반으로 새로운 제품을 생성합니다.
     *
     * @param command 제품을 생성하는 데 필요한 데이터가 포함된 명령입니다.
     * @return 생성된 제품의 ID입니다.
     */
    fun createProduct(command: CreateProductCommand): Long
}
