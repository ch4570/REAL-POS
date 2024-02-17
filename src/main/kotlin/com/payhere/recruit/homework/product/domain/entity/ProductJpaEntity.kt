package com.payhere.recruit.homework.product.domain.entity

import com.payhere.recruit.homework.product.domain.dto.request.ModifyProductCommand
import com.payhere.recruit.homework.common.entity.BaseTimeEntity
import com.payhere.recruit.homework.common.util.LocalDateTimeConverter.Companion.convertToLocalDateTime
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "PRODUCT")
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
    var expirationDate: LocalDateTime,

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
        expirationDate = convertToLocalDateTime(modifyProductCommand.expirationDate)
        productSize = modifyProductCommand.productSize
    }
}