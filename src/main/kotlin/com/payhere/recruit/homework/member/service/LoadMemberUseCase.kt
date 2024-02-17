package com.payhere.recruit.homework.member.service

import com.payhere.recruit.homework.member.domain.Member

/**
 * 회원을 로드하는 데 사용되는 유스케이스 인터페이스입니다.
 */
interface LoadMemberUseCase {
    /**
     * 전화번호에 해당하는 회원을 로드합니다.
     *
     * @param phoneNumber 로드할 회원의 전화번호입니다.
     * @return 로드된 회원입니다.
     */
    fun loadUserByPhoneNumber(phoneNumber: String): Member
}
