package com.payhere.recruit.homework.common.util

interface PasswordEncrypter {

    fun encodeString(rawPassword: String) : String
    fun matchPassword(rawPassword: String, encryptedText: String) : Boolean
}