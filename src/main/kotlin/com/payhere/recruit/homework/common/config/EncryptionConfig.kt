package com.payhere.recruit.homework.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * Configuration class for defining encryption-related beans.
 */
@Configuration
class EncryptionConfig {

    /**
     * Defines a bean for creating a PasswordEncoder instance.
     *
     * @return A PasswordEncoder instance created using PasswordEncoderFactories.
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()
}