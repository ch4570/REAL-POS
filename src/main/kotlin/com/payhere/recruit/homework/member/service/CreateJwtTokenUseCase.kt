package com.payhere.recruit.homework.member.service

/**
 * JWT 토큰을 생성하는 데 사용되는 유스케이스 인터페이스입니다.
 */
interface CreateJwtTokenUseCase {
    /**
     * 주어진 휴대폰 번호와 회원 ID에 대한 JWT 토큰을 생성합니다.
     *
     * @param phoneNumber 토큰과 관련된 휴대폰 번호입니다.
     * @param memberId 토큰과 관련된 회원의 ID입니다.
     * @return 생성된 JWT 토큰입니다.
     */
    fun createJwtToken(phoneNumber: String, memberId: Long): String
}
