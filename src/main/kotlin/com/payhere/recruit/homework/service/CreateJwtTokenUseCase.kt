package com.payhere.recruit.homework.service

interface CreateJwtTokenUseCase {
    fun createJwtToken(phoneNumber: String, memberId: Long) : String
}