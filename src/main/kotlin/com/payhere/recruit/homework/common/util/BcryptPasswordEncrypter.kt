package com.payhere.recruit.homework.common.util

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * Component class for encrypting passwords using BCrypt algorithm.
 *
 * @property passwordEncoder The PasswordEncoder instance for password encryption.
 */
@Component
class BcryptPasswordEncrypter(
    private val passwordEncoder: PasswordEncoder
) : PasswordEncrypter {

    /**
     * Encodes a raw password using BCrypt algorithm.
     *
     * @param rawPassword The raw password to encode.
     * @return The encoded password.
     */
    override fun encodeString(rawPassword: String): String = passwordEncoder.encode(rawPassword)

    /**
     * Matches a raw password with an encrypted text using BCrypt algorithm.
     *
     * @param rawPassword The raw password to match.
     * @param encryptedText The encrypted text to match against.
     * @return true if the raw password matches the encrypted text, false otherwise.
     */
    override fun matchPassword(rawPassword: String, encryptedText: String) =
        passwordEncoder.matches(rawPassword, encryptedText)
}
