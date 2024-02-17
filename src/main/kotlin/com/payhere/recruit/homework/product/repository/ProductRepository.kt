package com.payhere.recruit.homework.product.repository

import com.payhere.recruit.homework.product.domain.entity.ProductJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * 제품을 관리하는 데 사용되는 Repository 인터페이스입니다.
 */
interface ProductRepository : JpaRepository<ProductJpaEntity, Long> {

    /**
     * 주어진 제품 이름으로 제품의 존재 여부를 확인합니다.
     *
     * @param productName 존재 여부를 확인할 제품의 이름입니다.
     * @return 제품 이름으로 제품이 존재하면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
     */
    fun existsByProductName(productName: String): Boolean
}