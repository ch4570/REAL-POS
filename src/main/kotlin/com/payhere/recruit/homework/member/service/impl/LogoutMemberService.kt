package com.payhere.recruit.homework.member.service.impl

import com.payhere.recruit.homework.member.domain.dto.request.LogoutMemberCommand
import com.payhere.recruit.homework.member.repository.JwtTokenRepository
import com.payhere.recruit.homework.member.service.LogoutMemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 회원 로그아웃을 처리하기 위한 서비스 클래스입니다.
 *
 * @property jwtTokenRepository JWT 토큰 엔티티를 관리하기 위한 JwtTokenRepository 인스턴스입니다.
 */
@Service
@Transactional
class LogoutMemberService(
    private val jwtTokenRepository: JwtTokenRepository
) : LogoutMemberUseCase {

    /**
     * 제공된 로그아웃 명령을 기반으로 회원을 로그아웃합니다.
     *
     * @param logoutMemberCommand 로그아웃을 위한 액세스 토큰을 포함하는 로그아웃 명령입니다.
     */
    override fun logout(logoutMemberCommand: LogoutMemberCommand) {
        jwtTokenRepository.findByAccessToken(logoutMemberCommand.accessToken)
            .ifPresent { jwtTokenRepository.delete(it) }
    }
}
