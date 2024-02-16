package com.payletter.recruit.homework.common.util

interface InformationEncrypter {

    fun encryptString(plainText: String) : String
    fun decryptString(encryptString : String) : String
}