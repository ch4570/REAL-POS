package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.common.dto.request.CreateMemberCommand


interface CreateMemberUseCase {
    fun createUser(command: CreateMemberCommand) : Long
}