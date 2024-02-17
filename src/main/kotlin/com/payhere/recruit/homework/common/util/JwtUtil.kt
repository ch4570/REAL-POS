package com.payhere.recruit.homework.common.util

import com.payhere.recruit.homework.common.properties.JwtSecretProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

/**
 * Utility class for JWT token generation, verification, and extraction.
 *
 * @property jwtSecretProperties The JwtSecretProperties instance containing JWT secret key.
 */
@Component
class JwtUtil(
    private val jwtSecretProperties: JwtSecretProperties
) {

    private val secretKey = Base64.getEncoder()
        .encodeToString(jwtSecretProperties.secretKey.toByteArray())

    /**
     * Generates a JWT token for the given phone number.
     *
     * @param phoneNumber The phone number to include in the token.
     * @return The generated JWT token.
     */
    fun generateToken(phoneNumber: String): String {
        // Set token expiration period to 1 day (for smoother testing, the period is set longer)
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
     * Verifies the validity of a JWT token.
     *
     * @param token The JWT token to verify.
     * @return true if the token is valid, false otherwise.
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
     * Extracts the phone number from a JWT token.
     *
     * @param token The JWT token from which to extract the phone number.
     * @return The extracted phone number.
     */
    fun extractPhoneNumber(token: String): String =
        Jwts.parserBuilder().setSigningKey(secretKey.toByteArray())
            .build().parseClaimsJws(token)
            .body.subject
}
