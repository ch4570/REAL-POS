package com.payhere.recruit.homework.product.domain.dto.request

import com.payhere.recruit.homework.product.domain.entity.Size
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

/**
 * 제품을 수정하는 데 사용되는 명령을 나타내는 데이터 클래스입니다.
 *
 * @property categoryId 제품이 속한 카테고리의 ID입니다.
 * @property price 제품의 판매 가격입니다.
 * @property cost 제품의 원가입니다.
 * @property description 제품 설명입니다.
 * @property productName 제품 이름입니다.
 * @property barcode 제품 바코드입니다.
 * @property expirationDate 제품의 유통 기한입니다.
 * @property productSize 제품 크기입니다.
 */
data class ModifyProductCommand(
    @field:NotNull(message = "카테고리 아이디는 필수 입력 값입니다.")
    @field:Min(value = 1, message = "카테고리 아이디는 0 이상의 값이어야 합니다.")
    val categoryId: Long,

    @field:NotNull(message = "판매 가격은 필수 입력 값입니다.")
    @field:Min(value = 1, message = "판매 가격은 0 이상이어야 합니다.")
    val price: Int,

    @field:NotNull(message = "원가는 필수 입력 값입니다.")
    @field:Min(value = 1, message = "원가는 0 이상이어야 합니다.")
    val cost: Int,

    @field:NotBlank(message = "상품 설명은 필수 입력 값입니다.")
    val description: String,

    @field:NotBlank(message = "상품 이름은 필수 입력 값입니다.")
    val productName: String,

    @field:NotBlank(message = "바코드는 필수 입력 값입니다.")
    val barcode: String,

    @field:NotBlank(message = "유통 기한은 필수 입력 값입니다.")
    @field:Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\$",
        message = "날짜 형식에 맞지 않는 입력입니다. yyyy-MM-dd HH:mm:ss 형식을 맞춰주세요"
    )
    val expirationDate: String,

    @field:NotNull(message = "상품 사이즈는 필수 입력 값입니다.")
    val productSize: Size
)
