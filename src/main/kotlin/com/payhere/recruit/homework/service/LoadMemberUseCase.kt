package com.payhere.recruit.homework.service

import com.payhere.recruit.homework.domain.Member

interface LoadMemberUseCase {
    fun loadUserByPhoneNumber(phoneNumber: String) : Member
}