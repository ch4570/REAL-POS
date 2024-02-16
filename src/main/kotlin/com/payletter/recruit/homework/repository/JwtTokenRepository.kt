package com.payletter.recruit.homework.repository

import com.payletter.recruit.homework.domain.entity.JwtTokenJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface JwtTokenRepository : JpaRepository<JwtTokenJpaEntity, Long> {

    fun findByAccessToken(accessToken: String) : Optional<JwtTokenJpaEntity>
}