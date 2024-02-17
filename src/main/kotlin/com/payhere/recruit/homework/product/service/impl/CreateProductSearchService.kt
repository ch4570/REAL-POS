package com.payhere.recruit.homework.product.service.impl

import com.payhere.recruit.homework.product.domain.entity.ProductSearchJpaEntity
import com.payhere.recruit.homework.product.repository.ProductSearchRepository
import com.payhere.recruit.homework.product.service.CreateProductSearchUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 제품 검색을 생성하는 데 사용되는 서비스 클래스입니다.
 *
 * @property productSearchRepository 제품 검색 엔터티를 관리하는 ProductSearchRepository 인스턴스입니다.
 */
@Service
@Transactional
class CreateProductSearchService(
    private val productSearchRepository: ProductSearchRepository
) : CreateProductSearchUseCase {

    /**
     * 주어진 제품 ID와 검색 키워드를 사용하여 제품 검색 항목을 생성합니다.
     *
     * @param productId 검색과 관련된 제품의 ID입니다.
     * @param productSearchKeyword 제품과 연결할 검색 키워드입니다.
     */
    override fun createProductSearch(productId: Long, productSearchKeyword: String) {
        val productSearchEntity = ProductSearchJpaEntity(
            productId = productId,
            productSearchKeyword = convertToInitial(productSearchKeyword)
        )

        productSearchRepository.save(productSearchEntity)
    }

    private fun convertToInitial(productSearchKeyword: String): String {
        val result = StringBuilder()

        for (char in productSearchKeyword) {
            if (char == ' ') {
                result.append(' ')
                continue
            }
            val unicode = char.code - 44032
            if (unicode in 0 until 11172) { // 한글 유니코드 범위
                val initialIndex = unicode / 588
                val initialUnicode = initialIndex + 0x1100
                result.append(initialUnicode.toChar())
            }
        }

        return result.toString()
    }
}
