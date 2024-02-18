package com.payhere.recruit.homework.member.usecase

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.common.util.PasswordEncrypter
import com.payhere.recruit.homework.member.domain.dto.request.LoginMemberCommand
import com.payhere.recruit.homework.member.domain.entity.MemberJpaEntity
import com.payhere.recruit.homework.member.repository.MemberRepository
import com.payhere.recruit.homework.member.service.CreateJwtTokenUseCase
import com.payhere.recruit.homework.member.service.impl.LoginMemberService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.annotation.Import
import java.util.*

@Import(Fixture::class)
internal class LoginMemberUseCaseTest(
    private val fixture: Fixture
) : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val passwordEncrypter = mockk<PasswordEncrypter>()
    val memberRepository = mockk<MemberRepository>()
    val createJwtTokenUseCase = mockk<CreateJwtTokenUseCase>()
    val loginMemberUseCase = LoginMemberService(passwordEncrypter, memberRepository, createJwtTokenUseCase)

    Given("올바른 아이디와 비밀번호로 회원 로그인을 시도하는 상황에서") {
        val loginMemberCommand = fixture<LoginMemberCommand>()
        val memberEntity = fixture<MemberJpaEntity> {
            property<MemberJpaEntity, Long?>("memberId") { 1L }
        }
        val accessToken = "jwt access token"

        every { memberRepository.findByPhoneNumber(any()) } returns Optional.of(memberEntity)
        every { passwordEncrypter.matchPassword(any(), any()) } returns true
        every { createJwtTokenUseCase.createJwtToken(any(), any()) } returns accessToken

        When("로그인을 시도하면") {
            val actualResult = loginMemberUseCase.login(loginMemberCommand)

            Then("로그인이 성공해야 한다") {
                actualResult shouldBe accessToken
                verify(exactly = 1) { memberRepository.findByPhoneNumber(any()) }
                verify(exactly = 1) { passwordEncrypter.matchPassword(any(), any()) }
                verify(exactly = 1) { createJwtTokenUseCase.createJwtToken(any(), any()) }
            }
        }
    }

    Given("올바르지 않은 비밀번호로 회원 로그인을 시도하는 상황에서") {
        val loginMemberCommand = fixture<LoginMemberCommand>()
        val memberEntity = fixture<MemberJpaEntity> {
            property<MemberJpaEntity, Long?>("memberId") { 1L }
        }

        every { memberRepository.findByPhoneNumber(any()) } returns Optional.of(memberEntity)
        every { passwordEncrypter.matchPassword(any(), any()) } returns false

        When("로그인을 시도하면") {
            val exception = shouldThrow<CustomException> {
                loginMemberUseCase.login(loginMemberCommand)
            }

            Then("로그인이 실패해야 한다") {
                exception.errorCode shouldBe INVALID_LOGIN_ID_OR_PASSWORD
                verify(exactly = 1) { memberRepository.findByPhoneNumber(any()) }
                verify(exactly = 1) { passwordEncrypter.matchPassword(any(), any()) }
            }
        }
    }
})
