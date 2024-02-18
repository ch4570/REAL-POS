package com.payhere.recruit.homework.member.repository

import com.appmattus.kotlinfixture.kotlinFixture
import com.payhere.recruit.homework.member.domain.entity.MemberJpaEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
@DataJpaTest
internal class MemberRepositoryTest(
    private val memberRepository: MemberRepository
) : BehaviorSpec({

    val fixture = kotlinFixture()

    Given("회원을 조회하려고 하는 상황에서") {
        val phoneNumber = "010-1111-1111"
        val memberEntity = fixture<MemberJpaEntity> {
            property<MemberJpaEntity, String>("phoneNumber") { phoneNumber }
        }
        memberRepository.save(memberEntity)

        When("휴대폰 번호로 조회를 시도하면") {
            val actualResult = memberRepository.findByPhoneNumber(phoneNumber)

            Then("정상적으로 조회가 되어야 한다") {
                actualResult.get().phoneNumber shouldBe phoneNumber
            }
        }
    }

    Given("회원 존재 여부를 확인하려고 하는 상황에서") {
        val phoneNumber = "010-1111-1111"
        val memberEntity = fixture<MemberJpaEntity> {
            property<MemberJpaEntity, String>("phoneNumber") { phoneNumber }
        }
        memberRepository.save(memberEntity)

        When("휴대폰 번호로 확인을 시도하면") {
            val actualResult = memberRepository.existsByPhoneNumber(phoneNumber)

            Then("정상적으로 확인이 되어야 한다") {
                actualResult shouldBe true
            }
        }
    }
})

