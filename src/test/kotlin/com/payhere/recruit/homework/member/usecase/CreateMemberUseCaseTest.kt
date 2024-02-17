package com.payhere.recruit.homework.member.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.common.util.PasswordEncrypter
import com.payhere.recruit.homework.member.domain.dto.request.CreateMemberCommand
import com.payhere.recruit.homework.member.domain.entity.MemberJpaEntity
import com.payhere.recruit.homework.member.repository.MemberRepository
import com.payhere.recruit.homework.member.service.impl.CreateMemberService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

internal class CreateMemberUseCaseTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val memberRepository = mockk<MemberRepository>()
    val passwordEncrypter = mockk<PasswordEncrypter>()
    val createMemberUseCase = CreateMemberService(memberRepository, passwordEncrypter)
    val fixture = kotlinFixture()

    Given("존재하지 않는 신규 회원을 저장하려는 상황에서") {
        val encodedPassword = "encodePassword"
        val memberId = 1L

        val createMemberCommand = fixture<CreateMemberCommand>()
        val memberEntity = fixture<MemberJpaEntity> {
            property<MemberJpaEntity, Long?>("memberId") { memberId }
        }



        every { passwordEncrypter.encodeString(any()) } returns encodedPassword
        every { memberRepository.save(any()) } returns memberEntity
        every { memberRepository.existsByPhoneNumber(any()) } returns false

        When("회원 저장을 시도하면") {
            val actualResult = createMemberUseCase.createUser(createMemberCommand)

            Then("회원이 정상적으로 저장 되어야 한다") {
                actualResult shouldBe memberId
                verify(exactly = 1) { passwordEncrypter.encodeString(any()) }
                verify(exactly = 1) { memberRepository.save(any()) }
                verify(exactly = 1) { memberRepository.existsByPhoneNumber(any()) }
            }
        }
    }

    Given("중복 회원을 저장하려는 상황에서") {
        val createMemberCommand = fixture<CreateMemberCommand>()

        every { memberRepository.existsByPhoneNumber(any()) } returns true

        When("회원 저장을 시도하면") {
            val exception = shouldThrow<CustomException> {
                createMemberUseCase.createUser(createMemberCommand)
            }

            Then("회원이 정상적으로 저장 되어야 한다") {
                exception.errorCode shouldBe IS_ALREADY_EXISTS_MEMBER
                verify(exactly = 1) { memberRepository.existsByPhoneNumber(any()) }
            }
        }
    }
})