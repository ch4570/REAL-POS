package com.payhere.recruit.homework.member.usecase

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.member.domain.entity.MemberJpaEntity
import com.payhere.recruit.homework.member.repository.MemberRepository
import com.payhere.recruit.homework.member.service.impl.LoadMemberService
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
internal class LoadMemberUseCaseTest(
    private val fixture: Fixture
) : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val memberRepository = mockk<MemberRepository>()
    val loadMemberUseCase = LoadMemberService(memberRepository)

    Given("현재 존재하는 회원을 조회하려는 상황에서") {
        val phoneNumber = "010-1111-1111"

        val memberEntity = fixture<MemberJpaEntity> {
            property<MemberJpaEntity, String>("phoneNumber") { phoneNumber }
        }

        every { memberRepository.findByPhoneNumber(any()) } returns Optional.of(memberEntity)

        When("휴대폰 번호로 회원을 조회하면") {
            val actualResult = loadMemberUseCase.loadUserByPhoneNumber(phoneNumber)

            Then("회원이 정상적으로 조회 되어야 한다") {
                actualResult.phoneNumber shouldBe phoneNumber
                verify(exactly = 1) { memberRepository.findByPhoneNumber(any()) }
            }
        }
    }

    Given("현재 존재하지 않는 회원을 조회하려는 상황에서") {
        val phoneNumber = "010-1111-1111"

        every { memberRepository.findByPhoneNumber(any()) } returns Optional.empty()

        When("휴대폰 번호로 회원을 조회하면") {
            val exception = shouldThrow<CustomException> {
                loadMemberUseCase.loadUserByPhoneNumber(phoneNumber)
            }

            Then("회원이 정상적으로 조회 되어야 한다") {
                exception.errorCode shouldBe NOT_EXISTS_MEMBER
                verify(exactly = 1) { memberRepository.findByPhoneNumber(any()) }
            }
        }
    }
})