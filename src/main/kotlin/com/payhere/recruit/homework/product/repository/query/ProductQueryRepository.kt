package com.payhere.recruit.homework.product.repository.query

import com.payhere.recruit.homework.product.domain.dto.response.ProductDetailResponse
import com.payhere.recruit.homework.product.domain.dto.response.ProductResponse
import java.util.*

/**
 * 제품과 관련된 다양한 조회 쿼리를 실행하는 데 사용되는 리포지토리 인터페이스입니다.
 */
interface ProductQueryRepository {

    /**
     * 제품 ID를 기반으로 제품에 대한 세부 정보를 검색합니다.
     *
     * @param productId 세부 정보를 검색할 제품의 ID입니다.
     * @return 제품 세부 정보가 포함된 Optional입니다. 제품을 찾지 못한 경우 비어 있습니다.
     */
    fun loadProductDetail(productId: Long): Optional<ProductDetailResponse>

    /**
     * 텍스트 키워드 검색을 기반으로 제품 목록을 검색합니다.
     *
     * @param prevProductId 이전 페이지의 마지막 제품 ID입니다.
     * @param searchKeyword 제품 이름에서 검색할 키워드입니다.
     * @return 검색 기준과 일치하는 ProductResponse 객체 목록입니다.
     */
    fun loadProductListByTextKeyword(prevProductId: Long?, searchKeyword: String?): List<ProductResponse>

    /**
     * 초성 키워드 검색을 기반으로 제품 목록을 검색합니다.
     *
     * @param prevProductId 이전 페이지의 마지막 제품 ID입니다.
     * @param searchKeyword 제품 이름에서 초기 글자를 검색할 키워드입니다.
     * @return 검색 기준과 일치하는 ProductResponse 객체 목록입니다.
     */
    fun loadProductListByInitialKeyword(prevProductId: Long?, searchKeyword: String?): List<ProductResponse>
}