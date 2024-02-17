package com.payhere.recruit.homework.member.domain.dto.request

import jakarta.validation.constraints.NotBlank

/**
 * 회원 로그인을 위한 커맨드를 나타내는 데이터 클래스입니다.
 *
 * @property phoneNumber 회원의 휴대폰 번호입니다.
 * @property password 회원의 비밀번호입니다.
 */
data class LoginMemberCommand(
    @field:NotBlank(message = "휴대폰 번호는 필수 입력 항목입니다.")
    val phoneNumber: String,

    @field:NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    val password: String
)