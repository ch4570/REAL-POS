package com.payhere.recruit.homework.common.dto.request

import jakarta.validation.constraints.NotBlank


/**
 * Data class representing a command for member logout.
 *
 * @property accessToken The access token associated with the member session.
 */
data class LogoutMemberCommand(
    @field:NotBlank(message = "로그아웃시 토큰은 비어있을 수 없습니다.")
    val accessToken: String
)