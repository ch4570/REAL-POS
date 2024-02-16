package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.domain.entity.JwtTokenJpaEntity
import com.payletter.recruit.homework.repository.JwtTokenRepository
import com.payletter.recruit.homework.service.SaveJwtTokenUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SaveJwtTokenService(
    private val jwtTokenRepository: JwtTokenRepository
) : SaveJwtTokenUseCase {
    override fun saveJwtToken(accessToken: String, memberId: Long) {
       val jwtTokenEntity = JwtTokenJpaEntity(
           accessToken = accessToken,
           memberId = memberId
       )

        jwtTokenRepository.save(jwtTokenEntity)
    }
}