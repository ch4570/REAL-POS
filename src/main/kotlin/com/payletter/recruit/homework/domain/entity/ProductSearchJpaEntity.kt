package com.payletter.recruit.homework.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ProductSearchJpaEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_SEARCH_ID")
    val productSearchId: Long? = null,

    @Column(name = "PRODUCT_ID")
    val productId: Long,

    @Column(name = "PRODUCT_SEARCH_KEYWORD")
    val productSearchKeyword: String
) : BaseTimeEntity()