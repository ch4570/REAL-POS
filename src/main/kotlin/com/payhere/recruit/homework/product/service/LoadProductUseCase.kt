package com.payhere.recruit.homework.product.service

import com.payhere.recruit.homework.product.domain.dto.response.ProductDetailResponse
import com.payhere.recruit.homework.product.domain.dto.response.ProductResponse
import java.util.Optional

/**
 * 제품을 로드하는 데 사용되는 유스케이스 인터페이스입니다.
 */
interface LoadProductUseCase {

    /**
     * 제품 ID를 기반으로 제품의 세부 정보를 로드합니다.
     *
     * @param productId 로드할 제품의 ID입니다.
     * @return 제품 세부 정보를 포함하는 Optional입니다. 제품을 찾지 못한 경우 빈 Optional입니다.
     */
    fun loadProductDetail(productId: Long): Optional<ProductDetailResponse>

    /**
     * 선택적 매개변수를 기반으로 제품 목록을 로드합니다.
     *
     * @param prevProductId 이전 페이지의 마지막 제품의 ID입니다. 해당되지 않는 경우 null입니다.
     * @param searchKeyword 제품 이름에서 검색할 키워드입니다. 해당되지 않는 경우 null입니다.
     * @return 제품 목록입니다.
     */
    fun loadProductList(prevProductId: Long?, searchKeyword: String?): List<ProductResponse>
}
