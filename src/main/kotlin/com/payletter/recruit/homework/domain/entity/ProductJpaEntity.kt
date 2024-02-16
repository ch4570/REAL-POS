package com.payletter.recruit.homework.domain.entity

import jakarta.persistence.*

@Entity
class ProductJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    val productId: Long? = null,

    @Column(name = "CATEGORY_ID")
    val categoryId: Long,

    @Column(name = "PRODUCT_COST")
    val cost: Int,

    @Column(name = "PRODUCT_PRICE")
    val price: Int,

    @Column(name = "PRODUCT_NAME")
    val productName: String,

    @Column(name = "PRODUCT_DESCRIPTION")
    val description: String,

    @Column(name = "PRODUCT_BARCODE")
    val barcode: String,

    @Column(name = "PRODUCT_EXPIRATION_DATE")
    val expirationDate: String,

    @Column(name = "PRODUCT_SIZE")
    @Enumerated(EnumType.STRING)
    val productSize: Size

) : BaseTimeEntity()