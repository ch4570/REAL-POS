package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.dto.request.LogoutMemberCommand
import com.payhere.recruit.homework.repository.JwtTokenRepository
import com.payhere.recruit.homework.service.LogoutMemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


/**
 * Service class for logging out members.
 *
 * @property jwtTokenRepository The JwtTokenRepository instance for managing JWT token entities.
 */
@Service
@Transactional
class LogoutMemberService(
    private val jwtTokenRepository: JwtTokenRepository
) : LogoutMemberUseCase {

    /**
     * Logs out a member based on the provided logout command.
     *
     * @param logoutMemberCommand The logout command containing the access token to invalidate.
     */
    override fun logout(logoutMemberCommand: LogoutMemberCommand) {
        jwtTokenRepository.findByAccessToken(logoutMemberCommand.accessToken)
            .ifPresent { jwtTokenRepository.delete(it) }
    }
}