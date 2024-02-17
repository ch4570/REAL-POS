package com.payhere.recruit.homework.common.util

import com.payhere.recruit.homework.common.properties.JwtSecretProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

/**
 * JWT 토큰 생성, 검증 및 추출을 위한 유틸리티 클래스입니다.
 *
 * @property jwtSecretProperties JWT 비밀 키가 포함된 JwtSecretProperties 인스턴스입니다.
 */
@Component
class JwtUtil(
    private val jwtSecretProperties: JwtSecretProperties
) {

    private val secretKey = Base64.getEncoder()
        .encodeToString(jwtSecretProperties.secretKey.toByteArray())

    /**
     * 주어진 전화번호를 사용하여 JWT 토큰을 생성합니다.
     *
     * @param phoneNumber 토큰에 포함할 전화번호입니다.
     * @return 생성된 JWT 토큰입니다.
     */
    fun generateToken(phoneNumber: String): String {
        // 토큰 만료 기간을 1일로 설정합니다 (더 부드러운 테스트를 위해 기간을 더 길게 설정합니다).
        val tokenPeriod = 1000L * 60L * 60L * 24
        val claims = Jwts.claims().setSubject(phoneNumber)
        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenPeriod))
            .signWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS256.jcaName))
            .compact()
    }

    /**
     * JWT 토큰의 유효성을 검증합니다.
     *
     * @param token 검증할 JWT 토큰입니다.
     * @return 토큰이 유효하면 true, 그렇지 않으면 false입니다.
     */
    fun verifyToken(token: String) =
        try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.toByteArray())
                .build().parseClaimsJws(token)

            claims.body
                .expiration.after(Date())
        } catch (e: Exception) {
            false
        }

    /**
     * JWT 토큰에서 전화번호를 추출합니다.
     *
     * @param token 전화번호를 추출할 JWT 토큰입니다.
     * @return 추출된 전화번호입니다.
     */
    fun extractPhoneNumber(token: String): String =
        Jwts.parserBuilder().setSigningKey(secretKey.toByteArray())
            .build().parseClaimsJws(token)
            .body.subject
}

