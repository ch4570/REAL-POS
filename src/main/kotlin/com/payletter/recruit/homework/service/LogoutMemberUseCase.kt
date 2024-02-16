package com.payletter.recruit.homework.service

import com.payletter.recruit.homework.common.dto.request.LogoutMemberCommand

interface LogoutMemberUseCase {

    fun logout(logoutMemberCommand: LogoutMemberCommand)
}