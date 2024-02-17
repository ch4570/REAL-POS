package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.LogoutMemberCommand

/**
 * Use case interface for member logout.
 */
interface LogoutMemberUseCase {

    /**
     * Logs out a member.
     *
     * @param logoutMemberCommand The command containing logout details.
     */
    fun logout(logoutMemberCommand: LogoutMemberCommand)
}