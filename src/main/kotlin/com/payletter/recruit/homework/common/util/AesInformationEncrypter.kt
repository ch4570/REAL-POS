package com.payletter.recruit.homework.common.util

import org.springframework.security.crypto.encrypt.AesBytesEncryptor
import org.springframework.stereotype.Service

@Service
class AesInformationEncrypter(
    private val aesBytesEncryptor: AesBytesEncryptor
) : InformationEncrypter {

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
}