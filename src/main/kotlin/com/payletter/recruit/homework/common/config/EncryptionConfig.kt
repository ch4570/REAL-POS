package com.payletter.recruit.homework.common.config

import com.payletter.recruit.homework.common.properties.AesEncryptionProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.encrypt.AesBytesEncryptor
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class EncryptionConfig(
    private val aesEncryptionProperties: AesEncryptionProperties
) {



    @Bean
    fun passwordEncoder() : PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun aesBytesEncryptor() : AesBytesEncryptor =
        AesBytesEncryptor(aesEncryptionProperties.secretKey, aesEncryptionProperties.salt)
}