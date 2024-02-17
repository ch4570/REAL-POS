package com.payhere.recruit.homework.repository

import com.payhere.recruit.homework.domain.entity.MemberJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

/**
 * Repository interface for managing members.
 */
interface MemberRepository : JpaRepository<MemberJpaEntity, Long> {

    /**
     * Finds a member by their phone number.
     *
     * @param phoneNumber The phone number of the member to search for.
     * @return An optional containing the member entity if found, otherwise empty.
     */
    fun findByPhoneNumber(phoneNumber: String): Optional<MemberJpaEntity>

    /**
     * Checks if a member exists with the given phone number.
     *
     * @param phoneNumber The phone number to check for existence.
     * @return true if a member with the phone number exists, otherwise false.
     */
    fun existsByPhoneNumber(phoneNumber: String): Boolean
}