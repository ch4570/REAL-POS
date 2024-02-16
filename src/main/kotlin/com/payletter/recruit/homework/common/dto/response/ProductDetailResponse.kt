package com.payletter.recruit.homework.common.dto.response

import com.payletter.recruit.homework.domain.entity.Size
import java.time.LocalDateTime

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

