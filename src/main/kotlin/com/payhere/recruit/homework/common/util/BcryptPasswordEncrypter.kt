package com.payhere.recruit.homework.common.util

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * BCrypt 알고리즘을 사용하여 비밀번호를 암호화하는 클래스입니다.
 *
 * @property passwordEncoder 비밀번호 암호화를 위한 PasswordEncoder 인스턴스입니다.
 */
@Component
class BcryptPasswordEncrypter(
    private val passwordEncoder: PasswordEncoder
) : PasswordEncrypter {

    /**
     * BCrypt 알고리즘을 사용하여 평문 비밀번호를 암호화합니다.
     *
     * @param rawPassword 암호화할 평문 비밀번호입니다.
     * @return 암호화된 비밀번호입니다.
     */
    override fun encodeString(rawPassword: String): String = passwordEncoder.encode(rawPassword)

    /**
     * BCrypt 알고리즘을 사용하여 원시 비밀번호를 암호화된 텍스트와 일치시킵니다.
     *
     * @param rawPassword 일치시킬 평문 비밀번호입니다.
     * @param encryptedText 암호화된 텍스트와 일치시킬 텍스트입니다.
     * @return 평문 비밀번호가 암호화된 텍스트와 일치하면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
     */
    override fun matchPassword(rawPassword: String, encryptedText: String) =
        passwordEncoder.matches(rawPassword, encryptedText)
}
