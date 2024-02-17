package com.payhere.recruit.homework.member.service

import com.payhere.recruit.homework.member.domain.dto.request.LoginMemberCommand

/**
 * 회원 로그인에 사용되는 유스케이스 인터페이스입니다.
 */
interface LoginMemberUseCase {

    /**
     * 제공된 로그인 명령을 기반으로 회원을 로그인합니다.
     *
     * @param loginMemberCommand 로그인 자격 증명을 포함한 명령입니다.
     * @return 로그인한 회원을 나타내는 JWT 토큰입니다.
     */
    fun login(loginMemberCommand: LoginMemberCommand): String
}
