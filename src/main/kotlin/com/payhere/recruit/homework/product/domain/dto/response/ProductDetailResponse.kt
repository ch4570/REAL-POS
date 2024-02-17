package com.payhere.recruit.homework.product.domain.dto.response

import com.payhere.recruit.homework.product.domain.entity.Size
import java.time.LocalDateTime

/**
 * 제품 세부 정보 응답을 나타내는 데이터 클래스입니다.
 *
 * @property productId 제품의 고유 식별자입니다.
 * @property categoryName 제품이 속한 카테고리의 이름입니다.
 * @property cost 제품의 원가입니다.
 * @property price 제품의 판매 가격입니다.
 * @property productName 제품 이름입니다.
 * @property description 제품 설명입니다.
 * @property barcode 제품 바코드입니다.
 * @property expirationDate 제품의 유통 기한입니다.
 * @property productSize 제품 크기입니다.
 * @property regDate 제품의 등록 날짜입니다.
 * @property modDate 제품의 수정 날짜입니다.
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


