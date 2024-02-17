package com.payhere.recruit.homework.member.domain.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

/**
 * 회원을 생성하기 위한 커맨드를 나타내는 데이터 클래스입니다.
 *
 * @property phoneNumber 회원의 휴대폰 번호입니다.
 * @property password 회원의 비밀번호입니다.
 */
data class CreateMemberCommand(
    @field:NotBlank(message = "휴대폰 번호는 필수 입력 항목입니다.")
    @field:Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}\$", message = "휴대폰 번호 형식에 맞지 않습니다.")
    val phoneNumber: String,

    @field:NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @field:Size(min = 10, message = "비밀번호를 최소 10자리 이상으로 입력해야 합니다.")
    val password: String
)
