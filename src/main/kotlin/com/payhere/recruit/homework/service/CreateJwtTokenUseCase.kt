package com.payhere.recruit.homework.service

/**
 * Use case interface for creating JWT tokens.
 */
interface CreateJwtTokenUseCase {
    /**
     * Creates a JWT token for the given phone number and member ID.
     *
     * @param phoneNumber The phone number associated with the token.
     * @param memberId The ID of the member associated with the token.
     * @return The generated JWT token.
     */
    fun createJwtToken(phoneNumber: String, memberId: Long): String
}