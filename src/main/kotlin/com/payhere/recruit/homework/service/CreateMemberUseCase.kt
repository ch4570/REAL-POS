package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.CreateMemberCommand


/**
 * Use case interface for creating members.
 */
interface CreateMemberUseCase {
    /**
     * Creates a new member based on the provided command.
     *
     * @param command The command containing data for creating the member.
     * @return The ID of the created member.
     */
    fun createUser(command: CreateMemberCommand): Long
}