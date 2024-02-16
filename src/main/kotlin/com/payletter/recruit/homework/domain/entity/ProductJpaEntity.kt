package com.payletter.recruit.homework.domain.entity

import com.payletter.recruit.homework.common.dto.request.ModifyProductCommand
import jakarta.persistence.*

@Entity
class ProductJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    val productId: Long? = null,

    @Column(name = "CATEGORY_ID")
    var categoryId: Long,

    @Column(name = "PRODUCT_COST")
    var cost: Int,

    @Column(name = "PRODUCT_PRICE")
    var price: Int,

    @Column(name = "PRODUCT_NAME")
    var productName: String,

    @Column(name = "PRODUCT_DESCRIPTION")
    var description: String,

    @Column(name = "PRODUCT_BARCODE")
    var barcode: String,

    @Column(name = "PRODUCT_EXPIRATION_DATE")
    var expirationDate: String,

    @Column(name = "PRODUCT_SIZE")
    @Enumerated(EnumType.STRING)
    var productSize: Size

) : BaseTimeEntity() {
    fun modifyProduct(modifyProductCommand: ModifyProductCommand) {
        categoryId = modifyProductCommand.categoryId
        price = modifyProductCommand.price
        cost = modifyProductCommand.cost
        description = modifyProductCommand.description
        productName = modifyProductCommand.productName
        barcode = modifyProductCommand.barcode
        expirationDate = modifyProductCommand.expirationDate
        productSize = modifyProductCommand.productSize
    }
}