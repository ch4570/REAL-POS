package com.payhere.recruit.homework.product.service.impl

import com.payhere.recruit.homework.product.domain.dto.response.ProductResponse
import com.payhere.recruit.homework.product.repository.query.ProductQueryRepository
import com.payhere.recruit.homework.product.service.LoadProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 제품을 로드하는 데 사용되는 서비스 클래스입니다.
 *
 * @property productQueryRepository 제품 데이터를 쿼리하는 데 사용되는 ProductQueryRepository 인스턴스입니다.
 */
@Service
@Transactional(readOnly = true)
class LoadProductService(
    private val productQueryRepository: ProductQueryRepository
) : LoadProductUseCase {

    /**
     * 제품 ID를 기반으로 제품의 세부 정보를 로드합니다.
     *
     * @param productId 로드할 제품의 ID입니다.
     * @return 제품 세부 정보를 포함하는 Optional입니다. 제품을 찾지 못한 경우 비어 있습니다.
     */
    override fun loadProductDetail(productId: Long) =
        productQueryRepository.loadProductDetail(productId)


    /**
     * 선택적 매개변수를 기반으로 제품 목록을 로드합니다.
     *
     * @param prevProductId 이전 페이지의 마지막 제품 ID입니다. 해당되지 않는 경우 null입니다.
     * @param searchKeyword 제품 이름에서 검색할 키워드입니다. 해당되지 않는 경우 null입니다.
     * @return 제품 목록입니다.
     */
    override fun loadProductList(prevProductId: Long?, searchKeyword: String?) : List<ProductResponse> {
        if (isInitial(searchKeyword)) return productQueryRepository.loadProductListByInitialKeyword(prevProductId, searchKeyword)

        return productQueryRepository.loadProductListByTextKeyword(prevProductId, searchKeyword)
    }


    /**
     * 검색 키워드가 초성 키워드인지 확인합니다.
     *
     * @param searchKeyword 확인할 검색 키워드입니다.
     * @return 검색 키워드가 초성 키워드인 경우 true이고, 그렇지 않은 경우 false입니다.
     */
    private fun isInitial(searchKeyword: String?) : Boolean {
        if (searchKeyword.isNullOrBlank()) return false
        val unicode = searchKeyword[0].code
        return unicode in 0x3130..0x314E
    }
}
