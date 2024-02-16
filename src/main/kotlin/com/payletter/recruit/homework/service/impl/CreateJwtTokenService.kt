package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.common.util.JwtUtil
import com.payletter.recruit.homework.domain.entity.JwtTokenJpaEntity
import com.payletter.recruit.homework.repository.JwtTokenRepository
import com.payletter.recruit.homework.service.CreateJwtTokenUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateJwtTokenService(
    private val jwtUtil: JwtUtil,
    private val jwtTokenRepository: JwtTokenRepository
) : CreateJwtTokenUseCase {
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