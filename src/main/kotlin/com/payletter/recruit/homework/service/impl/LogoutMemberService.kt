package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.common.dto.request.LogoutMemberCommand
import com.payletter.recruit.homework.repository.JwtTokenRepository
import com.payletter.recruit.homework.service.LogoutMemberUseCase
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