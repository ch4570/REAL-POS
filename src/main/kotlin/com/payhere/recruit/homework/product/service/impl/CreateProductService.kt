package com.payhere.recruit.homework.product.service.impl

import com.payhere.recruit.homework.product.domain.dto.request.CreateProductCommand
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.product.repository.ProductRepository
import com.payhere.recruit.homework.product.service.CreateProductSearchUseCase
import com.payhere.recruit.homework.product.service.CreateProductUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 제품을 생성하는 데 사용되는 서비스 클래스입니다.
 *
 * @property productRepository 제품 엔터티를 관리하는 ProductRepository 인스턴스입니다.
 * @property createProductSearchUseCase 제품 검색을 생성하는 데 사용되는 CreateProductSearchUseCase 인스턴스입니다.
 */
@Service
@Transactional
class CreateProductService(
    private val productRepository: ProductRepository,
    private val createProductSearchUseCase: CreateProductSearchUseCase
) : CreateProductUseCase {

    /**
     * 제공된 명령을 기반으로 새로운 제품을 생성합니다.
     *
     * @param command 제품을 생성하는 데 필요한 데이터를 포함하는 명령입니다.
     * @return 생성된 제품의 ID입니다.
     * @throws CustomException 동일한 이름의 제품이 이미 존재하는 경우 예외가 발생합니다.
     */
    override fun createProduct(command: CreateProductCommand): Long {
        val productEntity = command.mapToJpaEntity()

        val isDuplicated = productRepository.existsByProductName(productEntity.productName)
        if (isDuplicated) throw CustomException(IS_ALREADY_EXISTS_PRODUCT)

        val savedEntity = productRepository.save(productEntity)
        createProductSearchUseCase.createProductSearch(
            productId = savedEntity.productId!!,
            productSearchKeyword = productEntity.productName
        )

        return savedEntity.productId
    }
}
