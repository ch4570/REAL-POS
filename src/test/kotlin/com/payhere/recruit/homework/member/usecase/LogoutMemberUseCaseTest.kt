package com.payhere.recruit.homework.member.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.payhere.recruit.homework.member.domain.dto.request.LogoutMemberCommand
import com.payhere.recruit.homework.member.domain.entity.JwtTokenJpaEntity
import com.payhere.recruit.homework.member.repository.JwtTokenRepository
import com.payhere.recruit.homework.member.service.impl.LogoutMemberService
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*
import java.util.*

internal class LogoutMemberUseCaseTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val jwtTokenRepository = mockk<JwtTokenRepository>()
    val logoutMemberUseCase = LogoutMemberService(jwtTokenRepository)
    val fixture = kotlinFixture()

    Given("현재 토큰이 유효한 상태로 로그아웃을 시도하는 상황에서") {
        val logoutMemberCommand = fixture<LogoutMemberCommand>()
        val jwtTokenEntity = fixture<JwtTokenJpaEntity>()

        every { jwtTokenRepository.findByAccessToken(any()) } returns Optional.of(jwtTokenEntity)
        every { jwtTokenRepository.delete(any()) } just runs

        When("로그아웃을 시도하면") {
            logoutMemberUseCase.logout(logoutMemberCommand)

            Then("로그아웃이 정상적으로 처리 되어야 한다") {
                verify(exactly = 1) { jwtTokenRepository.findByAccessToken(any()) }
                verify(exactly = 1) { jwtTokenRepository.delete(any()) }
            }
        }
    }

    Given("존재하지 않는 토큰으로 로그아웃을 시도하는 상황에서") {
        val logoutMemberCommand = fixture<LogoutMemberCommand>()

        every { jwtTokenRepository.findByAccessToken(any()) } returns Optional.empty()
        every { jwtTokenRepository.delete(any()) } just runs

        When("로그아웃을 시도하면") {
            logoutMemberUseCase.logout(logoutMemberCommand)

            Then("JWT 삭제를 위해 데이터베이스에 접근하지 않아야 한다") {
                verify(exactly = 1) { jwtTokenRepository.findByAccessToken(any()) }
                verify(exactly = 0) { jwtTokenRepository.delete(any()) }
            }
        }
    }
})