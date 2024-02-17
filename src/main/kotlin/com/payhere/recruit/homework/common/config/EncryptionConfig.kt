package com.payhere.recruit.homework.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * 암호화 관련 유틸 Bean을 정의하는 설정 클래스입니다.
 */
@Configuration
class EncryptionConfig {

    /**
     * PasswordEncoder 인스턴스를 생성하기 위한 빈을 정의합니다.
     *
     * @return PasswordEncoderFactories를 사용하여 생성된 PasswordEncoder 인스턴스 입니다.
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()
}