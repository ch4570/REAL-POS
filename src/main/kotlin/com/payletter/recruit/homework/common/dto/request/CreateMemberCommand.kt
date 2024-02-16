package com.payletter.recruit.homework.common.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateMemberCommand(
    @field:NotBlank(message = "휴대폰 번호는 필수 입력 항목입니다.")
    @field:Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}\$", message = "휴대폰 번호 형식에 맞지 않습니다.")
    val phoneNumber: String,

    @field:NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @field:Size(min = 10, message = "비밀번호를 최소 10자리 이상으로 입력해야 합니다.")
    val password: String
)