package com.payhere.recruit.homework.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * JWT 비밀 키를 위한 프로퍼티 클래스입니다.
 *
 * @property secretKey JWT 토큰 생성 및 확인에 사용되는 비밀 키입니다.
 */
@ConfigurationProperties(prefix = "jwt")
class JwtSecretProperties(
    val secretKey: String
)
