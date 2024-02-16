package com.payletter.recruit.homework.service

import com.payletter.recruit.homework.common.dto.request.LoginMemberCommand

interface LoginMemberUseCase {

    fun login(loginMemberCommand: LoginMemberCommand) : String
}