package com.payhere.recruit.homework.common.util

/**
 * Interface for password encryption and matching operations.
 */
interface PasswordEncrypter {

    /**
     * Encodes a raw password.
     *
     * @param rawPassword The raw password to encode.
     * @return The encoded password.
     */
    fun encodeString(rawPassword: String): String

    /**
     * Matches a raw password with an encrypted text.
     *
     * @param rawPassword The raw password to match.
     * @param encryptedText The encrypted text to match against.
     * @return true if the raw password matches the encrypted text, false otherwise.
     */
    fun matchPassword(rawPassword: String, encryptedText: String): Boolean
}