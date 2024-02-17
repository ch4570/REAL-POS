package com.payhere.recruit.homework.common.dto.response

import java.time.LocalDateTime

/**
 * Data class representing the response for product details.
 *
 * @property productId The unique identifier of the product.
 * @property price The selling price of the product.
 * @property productName The name of the product.
 * @property regDate The registration date of the product.
 * @property modDate The modification date of the product.
 */
data class ProductResponse(
    val productId: Long,
    val price: Int,
    val productName: String,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)