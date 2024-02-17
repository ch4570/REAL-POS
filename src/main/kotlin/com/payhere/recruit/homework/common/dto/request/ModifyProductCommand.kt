package com.payhere.recruit.homework.common.dto.request

import com.payhere.recruit.homework.domain.entity.Size
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

/**
 * Data class representing a command for modifying a product.
 *
 * @property categoryId The ID of the category to which the product belongs.
 * @property price The selling price of the product.
 * @property cost The cost of the product.
 * @property description The description of the product.
 * @property productName The name of the product.
 * @property barcode The barcode of the product.
 * @property expirationDate The expiration date of the product.
 * @property productSize The size of the product.
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
        message = "날짜 형식에 맞지 않는 입력입니다. yyyy-MM-dd HH:mm:ss 형식을 맞춰주세요")
    val expirationDate: String,

    @field:NotNull(message = "상품 사이즈는 필수 입력 값입니다.")
    val productSize: Size
)