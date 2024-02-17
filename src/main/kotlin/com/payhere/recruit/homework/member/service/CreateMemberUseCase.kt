package com.payhere.recruit.homework.member.service

import com.payhere.recruit.homework.member.domain.dto.request.CreateMemberCommand


/**
 * 회원을 생성하는 데 사용되는 유스케이스 인터페이스입니다.
 */
interface CreateMemberUseCase {
    /**
     * 제공된 명령을 기반으로 새 회원을 생성합니다.
     *
     * @param command 회원 생성에 필요한 데이터가 포함된 명령입니다.
     * @return 생성된 회원의 ID입니다.
     */
    fun createUser(command: CreateMemberCommand): Long
}
