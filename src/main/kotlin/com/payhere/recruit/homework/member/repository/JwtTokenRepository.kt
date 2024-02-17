package com.payhere.recruit.homework.member.repository

import com.payhere.recruit.homework.member.domain.entity.JwtTokenJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

/**
 * JWT 토큰을 관리하기 위한 리포지토리 인터페이스입니다.
 */
interface JwtTokenRepository : JpaRepository<JwtTokenJpaEntity, Long> {

    /**
     * 주어진 액세스 토큰으로 JWT 토큰 엔티티를 찾습니다.
     *
     * @param accessToken 찾을 액세스 토큰입니다.
     * @return JWT 토큰 엔티티가 발견되면 해당하는 Optional을 반환하고, 그렇지 않으면 비어 있는 Optional을 반환합니다.
     */
    fun findByAccessToken(accessToken: String): Optional<JwtTokenJpaEntity>
}
