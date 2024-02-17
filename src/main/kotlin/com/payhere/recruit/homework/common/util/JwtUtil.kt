package com.payhere.recruit.homework.common.util

import com.payhere.recruit.homework.common.properties.JwtSecretProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtil(
    private val jwtSecretProperties: JwtSecretProperties
) {

    private val secretKey = Base64.getEncoder()
        .encodeToString(jwtSecretProperties.secretKey.toByteArray())

    fun generateToken(phoneNumber: String) : String {
        // 토큰 유효기간을 1일로 설정 -> 원활한 테스트를 위해 기간을 길게 산정
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

    fun extractPhoneNumber(token: String): String =
        Jwts.parserBuilder().setSigningKey(secretKey.toByteArray())
            .build().parseClaimsJws(token)
            .body.subject
}