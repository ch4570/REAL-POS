package com.payletter.recruit.homework.service

interface CreateJwtTokenUseCase {
    fun createJwtToken(phoneNumber: String, memberId: Long) : String
}