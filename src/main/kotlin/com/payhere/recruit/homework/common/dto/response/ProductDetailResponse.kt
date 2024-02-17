package com.payhere.recruit.homework.common.dto.response

import com.payhere.recruit.homework.domain.entity.Size
import java.time.LocalDateTime

/**
 * Data class representing the response for product details.
 *
 * @property productId The unique identifier of the product.
 * @property categoryName The name of the category to which the product belongs.
 * @property cost The cost of the product.
 * @property price The selling price of the product.
 * @property productName The name of the product.
 * @property description The description of the product.
 * @property barcode The barcode of the product.
 * @property expirationDate The expiration date of the product.
 * @property productSize The size of the product.
 * @property regDate The registration date of the product.
 * @property modDate The modification date of the product.
 */
data class ProductDetailResponse(
    val productId: Long,
    val categoryName: String,
    val cost: Int,
    val price: Int,
    val productName: String,
    val description: String,
    val barcode: String,
    val expirationDate: LocalDateTime,
    val productSize: Size,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)

