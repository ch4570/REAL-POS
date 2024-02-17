package com.payhere.recruit.homework.repository

import com.payhere.recruit.homework.domain.entity.JwtTokenJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

/**
 * Repository interface for managing JWT tokens.
 */
interface JwtTokenRepository : JpaRepository<JwtTokenJpaEntity, Long> {

    /**
     * Finds a JWT token by its access token.
     *
     * @param accessToken The access token to search for.
     * @return An optional containing the JWT token entity if found, otherwise empty.
     */
    fun findByAccessToken(accessToken: String): Optional<JwtTokenJpaEntity>
}