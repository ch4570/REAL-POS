package com.payletter.recruit.homework.common.util

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class BcryptPasswordEncrypter(
    private val passwordEncoder: PasswordEncoder
) : PasswordEncrypter{

    override fun encodeString(rawPassword: String): String = passwordEncoder.encode(rawPassword)

    override fun matchPassword(rawPassword: String, encryptedText: String) =
        passwordEncoder.matches(rawPassword, encryptedText)
}