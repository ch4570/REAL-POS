package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.domain.Member

/**
 * Use case interface for loading members.
 */
interface LoadMemberUseCase {
    /**
     * Loads a member by their phone number.
     *
     * @param phoneNumber The phone number of the member to load.
     * @return The loaded member.
     */
    fun loadUserByPhoneNumber(phoneNumber: String): Member
}