package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.LoginMemberCommand

/**
 * Use case interface for member login.
 */
interface LoginMemberUseCase {

    /**
     * Logs in a member based on the provided login command.
     *
     * @param loginMemberCommand The command containing login credentials.
     * @return The JWT token representing the logged-in member.
     */
    fun login(loginMemberCommand: LoginMemberCommand): String
}