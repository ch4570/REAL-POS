package com.payletter.recruit.homework.service

import com.payletter.recruit.homework.common.dto.request.CreateMemberCommand

interface CreateMemberUseCase {
    fun createUser(command: CreateMemberCommand) : Long
}