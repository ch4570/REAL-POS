package com.payhere.recruit.homework.common.dto.request

import jakarta.validation.constraints.NotBlank

data class LoginMemberCommand(
    @field:NotBlank(message = "휴대폰 번호는 필수 입력 항목입니다.")
    val phoneNumber: String,

    @field:NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    val password: String
)