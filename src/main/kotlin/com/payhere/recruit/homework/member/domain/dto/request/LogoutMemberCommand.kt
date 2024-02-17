package com.payhere.recruit.homework.member.domain.dto.request

import jakarta.validation.constraints.NotBlank


/**
 * 회원 로그아웃을 위한 커맨드를 나타내는 데이터 클래스입니다.
 *
 * @property accessToken 회원과 관련된 액세스 토큰입니다.
 */
data class LogoutMemberCommand(
    @field:NotBlank(message = "로그아웃시 토큰은 비어있을 수 없습니다.")
    val accessToken: String
)