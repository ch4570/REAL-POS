package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.dto.request.LogoutMemberCommand
import com.payhere.recruit.homework.repository.JwtTokenRepository
import com.payhere.recruit.homework.service.LogoutMemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LogoutMemberService(
    private val jwtTokenRepository: JwtTokenRepository
) : LogoutMemberUseCase {

    override fun logout(logoutMemberCommand: LogoutMemberCommand) {
        jwtTokenRepository.findByAccessToken(logoutMemberCommand.accessToken)
            .ifPresent { jwtTokenRepository.delete(it) }
    }


}