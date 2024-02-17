package com.payhere.recruit.homework.product.domain.dto.response

import java.time.LocalDateTime

/**
 * 제품 세부 정보 응답을 나타내는 데이터 클래스입니다.
 *
 * @property productId 제품의 고유 식별자입니다.
 * @property price 제품의 판매 가격입니다.
 * @property productName 제품 이름입니다.
 * @property regDate 제품의 등록 날짜입니다.
 * @property modDate 제품의 수정 날짜입니다.
 */
data class ProductResponse(
    val productId: Long,
    val price: Int,
    val productName: String,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)