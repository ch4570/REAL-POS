package com.payletter.recruit.homework.repository

import com.payletter.recruit.homework.domain.entity.MemberJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberRepository : JpaRepository<MemberJpaEntity, Long> {

    fun findByPhoneNumber(phoneNumber: String) : Optional<MemberJpaEntity>
}