package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.util.JwtUtil
import com.payhere.recruit.homework.domain.entity.JwtTokenJpaEntity
import com.payhere.recruit.homework.repository.JwtTokenRepository
import com.payhere.recruit.homework.service.CreateJwtTokenUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service class for creating JWT tokens.
 *
 * @property jwtUtil The JwtUtil instance for generating tokens.
 * @property jwtTokenRepository The JwtTokenRepository instance for managing token entities.
 */
@Service
@Transactional
class CreateJwtTokenService(
    private val jwtUtil: JwtUtil,
    private val jwtTokenRepository: JwtTokenRepository
) : CreateJwtTokenUseCase {

    /**
     * Creates a JWT token for the specified phone number and member ID.
     *
     * @param phoneNumber The phone number associated with the token.
     * @param memberId The ID of the member associated with the token.
     * @return The generated JWT token.
     */
    override fun createJwtToken(phoneNumber: String, memberId: Long): String {
        val accessToken = jwtUtil.generateToken(phoneNumber)

        val jwtTokenEntity = JwtTokenJpaEntity(
            accessToken = accessToken,
            memberId = memberId
        )

        jwtTokenRepository.save(jwtTokenEntity)

        return accessToken
    }
}