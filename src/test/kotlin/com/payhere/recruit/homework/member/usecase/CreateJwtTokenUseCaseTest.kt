package com.payhere.recruit.homework.member.usecase

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.common.util.JwtUtil
import com.payhere.recruit.homework.member.domain.entity.JwtTokenJpaEntity
import com.payhere.recruit.homework.member.repository.JwtTokenRepository
import com.payhere.recruit.homework.member.service.impl.CreateJwtTokenService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.annotation.Import

@Import(Fixture::class)
internal class CreateJwtTokenUseCaseTest(
    private val fixture: Fixture
) : BehaviorSpec({

    val jwtUtil = mockk<JwtUtil>()
    val jwtTokenRepository = mockk<JwtTokenRepository>()
    val createJwtTokenUseCase = CreateJwtTokenService(jwtUtil, jwtTokenRepository)

    Given("JWT 토큰을 생성하려는 상황에서") {
        val phoneNumber = "010-1111-1111"
        val memberId = 1L

        val accessToken = "jwt access token"
        val jwtTokenEntity = fixture<JwtTokenJpaEntity> {
            property<JwtTokenJpaEntity, String>("accessToken") { accessToken }
        }

        every { jwtUtil.generateToken(any()) } returns accessToken
        every { jwtTokenRepository.save(any()) } returns jwtTokenEntity

        When("토큰 생성을 시도하면") {
            val actualResult = createJwtTokenUseCase.createJwtToken(phoneNumber, memberId)

            Then("토큰 생성이 정상적으로 되어야 한다") {
                actualResult shouldBe accessToken
                jwtTokenEntity.accessToken shouldBe accessToken
                verify(exactly = 1) { jwtUtil.generateToken(any()) }
                verify(exactly = 1) { jwtTokenRepository.save(any()) }
            }
        }
    }
})