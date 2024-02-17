package com.payhere.recruit.homework.common.dto.response

import java.time.LocalDateTime

data class ProductResponse(
    val productId: Long,
    val price: Int,
    val productName: String,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)