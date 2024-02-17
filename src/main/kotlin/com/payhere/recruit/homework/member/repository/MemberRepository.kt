package com.payhere.recruit.homework.member.repository

import com.payhere.recruit.homework.member.domain.entity.MemberJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

/**
 * 회원을 관리하기 위한 리포지토리 인터페이스입니다.
 */
interface MemberRepository : JpaRepository<MemberJpaEntity, Long> {

    /**
     * 휴대폰 번호로 회원을 찾습니다.
     *
     * @param phoneNumber 찾을 회원의 휴대폰 번호입니다.
     * @return 발견된 회원 엔티티를 포함하는 Optional을 반환하고, 그렇지 않으면 비어 있는 Optional을 반환합니다.
     */
    fun findByPhoneNumber(phoneNumber: String): Optional<MemberJpaEntity>

    /**
     * 주어진 휴대폰 번호로 회원이 존재하는지 확인합니다.
     *
     * @param phoneNumber 존재 여부를 확인할 휴대폰 번호입니다.
     * @return 주어진 휴대폰 번호를 가진 회원이 존재하면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
     */
    fun existsByPhoneNumber(phoneNumber: String): Boolean
}