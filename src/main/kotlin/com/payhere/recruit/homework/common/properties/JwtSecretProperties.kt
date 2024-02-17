package com.payhere.recruit.homework.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Configuration properties class for JWT secret key.
 *
 * @property secretKey The secret key used for JWT token generation and verification.
 */
@ConfigurationProperties(prefix = "jwt")
class JwtSecretProperties(
    val secretKey: String
)