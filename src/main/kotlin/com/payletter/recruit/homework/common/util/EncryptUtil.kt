package com.payletter.recruit.homework.common.util

import org.springframework.security.crypto.encrypt.AesBytesEncryptor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class EncryptUtil(
    private val aesBytesEncryptor: AesBytesEncryptor,
    private val passwordEncoder: PasswordEncoder
) : InformationEncrypter, PasswordEncrypter{
    // AES 암호화
    override fun encryptString(plainText: String): String {
        val encryptedByteArray = aesBytesEncryptor.encrypt(plainText.toByteArray(Charsets.UTF_8))
        return encryptedByteArray.toString(Charsets.UTF_8)
    }

    // AES 복호화
    override fun decryptString(encryptString: String): String {
        val decryptedByteArray = aesBytesEncryptor.decrypt(encryptString.toByteArray(Charsets.UTF_8))
        return decryptedByteArray.toString(Charsets.UTF_8)
    }

    override fun encodeString(rawPassword: String): String = passwordEncoder.encode(rawPassword)

    override fun matchPassword(rawPassword: String, encryptedText: String) =
        passwordEncoder.matches(rawPassword, encryptedText)
}