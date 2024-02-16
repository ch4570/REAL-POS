package com.payletter.recruit.homework.common.config

import com.payletter.recruit.homework.common.properties.AesEncryptionProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.encrypt.AesBytesEncryptor
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val aesEncryptionProperties: AesEncryptionProperties
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { request ->
                request.requestMatchers(HttpMethod.POST, "/api/members/**").permitAll()
                request.requestMatchers(HttpMethod.POST, "/api/login/**").permitAll()
                request.requestMatchers(HttpMethod.POST, "/api/logout/**").permitAll()
                request.anyRequest().authenticated()
            }
            .build()
    }


    @Bean
    fun passwordEncoder() : PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun aesBytesEncryptor() : AesBytesEncryptor =
        AesBytesEncryptor(aesEncryptionProperties.secretKey, aesEncryptionProperties.salt)
}