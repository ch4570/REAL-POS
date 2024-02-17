package com.payhere.recruit.homework.member.service.impl

import com.payhere.recruit.homework.common.util.JwtUtil
import com.payhere.recruit.homework.member.domain.entity.JwtTokenJpaEntity
import com.payhere.recruit.homework.member.repository.JwtTokenRepository
import com.payhere.recruit.homework.member.service.CreateJwtTokenUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 토큰을 생성하기 위한 서비스 클래스입니다.
 *
 * @property jwtUtil 토큰 생성을 위한 JwtUtil 인스턴스입니다.
 * @property jwtTokenRepository 토큰 엔티티를 관리하기 위한 JwtTokenRepository 인스턴스입니다.
 */
@Service
@Transactional
class CreateJwtTokenService(
    private val jwtUtil: JwtUtil,
    private val jwtTokenRepository: JwtTokenRepository
) : CreateJwtTokenUseCase {

    /**
     * 지정된 휴대폰 번호와 회원 ID에 대한 JWT 토큰을 생성합니다.
     *
     * @param phoneNumber 토큰과 연결된 휴대폰 번호입니다.
     * @param memberId 토큰과 연결된 회원의 ID입니다.
     * @return 생성된 JWT 토큰입니다.
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
