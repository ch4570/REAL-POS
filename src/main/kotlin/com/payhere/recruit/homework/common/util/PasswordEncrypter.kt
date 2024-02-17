package com.payhere.recruit.homework.common.util

/**
 * 비밀번호 암호화 및 비밀번호 검증을 위한 인터페이스입니다.
 */
interface PasswordEncrypter {

    /**
     * 원시 비밀번호를 암호화합니다.
     *
     * @param rawPassword 암호화할 평문 비밀번호입니다.
     * @return 암호화된 비밀번호입니다.
     */
    fun encodeString(rawPassword: String): String

    /**
     * 원시 비밀번호와 암호화된 텍스트를 비교합니다.
     *
     * @param rawPassword 비교할 평문 비밀번호입니다.
     * @param encryptedText 비교 대상인 암호화된 텍스트입니다.
     * @return 평문 비밀번호가 암호화된 텍스트와 일치하면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
     */
    fun matchPassword(rawPassword: String, encryptedText: String): Boolean
}