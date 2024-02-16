package com.payletter.recruit.homework.service

import com.payletter.recruit.homework.domain.Member

interface LoadMemberUseCase {
    fun loadUserByPhoneNumber(phoneNumber: String) : Member
}