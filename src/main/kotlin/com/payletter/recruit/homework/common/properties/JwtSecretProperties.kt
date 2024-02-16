package com.payletter.recruit.homework.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
class JwtSecretProperties(
    val secretKey: String
)