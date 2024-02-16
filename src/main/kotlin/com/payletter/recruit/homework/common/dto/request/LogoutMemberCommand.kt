package com.payletter.recruit.homework.common.dto.request

import jakarta.validation.constraints.NotBlank

data class LogoutMemberCommand(
    @field:NotBlank(message = "로그아웃시 토큰은 비어있을 수 없습니다.")
    val accessToken: String
)