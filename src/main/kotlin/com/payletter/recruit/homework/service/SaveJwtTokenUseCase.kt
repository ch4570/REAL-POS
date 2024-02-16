package com.payletter.recruit.homework.service

interface SaveJwtTokenUseCase {
    fun saveJwtToken(accessToken: String, memberId: Long)
}