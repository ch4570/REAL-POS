package com.payhere.recruit.homework.common.dto.request

import jakarta.validation.constraints.NotBlank

/**
 * Data class representing a command for member login.
 *
 * @property phoneNumber The phone number of the member.
 * @property password The password of the member.
 */
data class LoginMemberCommand(
    @field:NotBlank(message = "휴대폰 번호는 필수 입력 항목입니다.")
    val phoneNumber: String,

    @field:NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    val password: String
)