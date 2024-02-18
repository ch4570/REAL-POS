package com.payhere.recruit.homework.member.repository

import com.appmattus.kotlinfixture.Fixture
import com.payhere.recruit.homework.member.domain.entity.JwtTokenJpaEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(Fixture::class)
internal class JwtTokenRepositoryTest(
    private val jwtTokenRepository: JwtTokenRepository,
    private val fixture: Fixture
) : BehaviorSpec({

    Given("JWT 토큰 엔티티를 조회하려고 하는 상황에서") {
        val jwtTokenEntity = fixture<JwtTokenJpaEntity> {
            property<JwtTokenJpaEntity, String>("accessToken") { "generatedToken" }
        }

        val savedJwtTokenEntity = jwtTokenRepository.save(jwtTokenEntity)

        When("생성된 액세스 토큰으로 조회를 시도하면") {
            val findJwtTokenEntity =
                jwtTokenRepository.findByAccessToken(savedJwtTokenEntity.accessToken).get()

            Then("정상적으로 조회가 되어야 한다") {
                findJwtTokenEntity.tokenId shouldBe savedJwtTokenEntity.tokenId
                findJwtTokenEntity.accessToken shouldBe savedJwtTokenEntity.accessToken
            }
        }
    }
})