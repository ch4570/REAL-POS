package com.payletter.recruit.homework.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "encrypt.aes")
class AesEncryptionProperties(
    val secretKey: String,
    val algorithm: String
)