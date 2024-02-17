package com.payhere.recruit.homework.product.service

/**
 * 제품 검색을 생성하는 데 사용되는 유스케이스 인터페이스입니다.
 */
interface CreateProductSearchUseCase {

    /**
     * 주어진 제품 ID 및 검색 키워드와 관련된 제품 검색 항목을 생성합니다.
     *
     * @param productId 검색과 관련된 제품의 ID입니다.
     * @param productSearchKeyword 제품과 연관된 검색 키워드입니다.
     */
    fun createProductSearch(productId: Long, productSearchKeyword: String)
}
