package com.payhere.recruit.homework.product.domain.entity

import com.payhere.recruit.homework.common.entity.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "PRODUCT_SEARCH")
class ProductSearchJpaEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_SEARCH_ID")
    val productSearchId: Long? = null,

    @Column(name = "PRODUCT_ID")
    val productId: Long,

    @Column(name = "PRODUCT_SEARCH_KEYWORD")
    val productSearchKeyword: String
) : BaseTimeEntity()