package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.LoginMemberCommand

interface LoginMemberUseCase {

    fun login(loginMemberCommand: LoginMemberCommand) : String
}