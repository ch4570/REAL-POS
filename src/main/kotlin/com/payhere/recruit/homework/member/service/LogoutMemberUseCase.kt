package com.payhere.recruit.homework.member.service

import com.payhere.recruit.homework.member.domain.dto.request.LogoutMemberCommand

/**
 * 회원 로그아웃에 사용되는 유스케이스 인터페이스입니다.
 */
interface LogoutMemberUseCase {

    /**
     * 제공된 로그아웃 명령을 기반으로 회원을 로그아웃합니다.
     *
     * @param logoutMemberCommand 로그아웃 세부 정보를 포함한 명령입니다.
     */
    fun logout(logoutMemberCommand: LogoutMemberCommand)
}
