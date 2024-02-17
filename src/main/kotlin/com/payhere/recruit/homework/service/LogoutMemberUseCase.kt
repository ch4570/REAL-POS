package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.LogoutMemberCommand

interface LogoutMemberUseCase {

    fun logout(logoutMemberCommand: LogoutMemberCommand)
}