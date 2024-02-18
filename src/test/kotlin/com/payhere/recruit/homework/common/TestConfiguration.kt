package com.payhere.recruit.homework.common

import com.appmattus.kotlinfixture.kotlinFixture
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfiguration {

    @Bean
    fun fixture() = kotlinFixture()
}