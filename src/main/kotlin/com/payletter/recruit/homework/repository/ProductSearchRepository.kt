package com.payletter.recruit.homework.repository

import com.payletter.recruit.homework.domain.entity.ProductSearchJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductSearchRepository : JpaRepository<ProductSearchJpaEntity, Long> {

    @Query("SELECT * FROM product_search_jpa_entity where product_search_keyword like '%ㅈㄱ%'", nativeQuery =  true)
    fun findByProductSearchKeyword(keyword: String) : ProductSearchJpaEntity
}