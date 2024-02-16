package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.domain.entity.ProductSearchJpaEntity
import com.payletter.recruit.homework.repository.ProductSearchRepository
import com.payletter.recruit.homework.service.CreateProductSearchUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateProductSearchService(
    private val productSearchRepository: ProductSearchRepository
) : CreateProductSearchUseCase {
    override fun createProductSearch(productId: Long, productSearchKeyword: String) {
        println("초성 저장 -> 원본 {$productSearchKeyword}, 변환본 {${convertToInitial(productSearchKeyword)}}")
        val productSearchEntity = ProductSearchJpaEntity(
            productId = productId,
            productSearchKeyword = convertToInitial(productSearchKeyword)
        )

        println("검색어 저장 => {${productSearchEntity.productSearchKeyword}}")

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