package com.payhere.recruit.homework.member.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.payhere.recruit.homework.common.exception.GlobalCatcher
import com.payhere.recruit.homework.member.domain.dto.request.LoginMemberCommand
import com.payhere.recruit.homework.member.service.LoginMemberUseCase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@SpringBootTest
internal class LoginMemberControllerTest(
    private val objectMapper: ObjectMapper
) : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val loginMemberUseCase = mockk<LoginMemberUseCase>()
    lateinit var mockMvc: MockMvc

    Given("로그인(정상 케이스) 요청을 하는 상황에서") {
        val expectedResult = "accessToken"
        every { loginMemberUseCase.login(any()) } returns expectedResult

        mockMvc = MockMvcBuilders.standaloneSetup(
            LoginMemberController(loginMemberUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("필요한 데이터를 모두 담아, 로그인 시도를 하면") {
            val loginMemberCommand = LoginMemberCommand(
                phoneNumber = "010-1111-1111",
                password = "payhere12345!!"
            )

            val resultActions = mockMvc.perform(
                post("/api/login")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(loginMemberCommand)))
                .andDo(print())

            Then("요청이 정상적으로 처리되어야 한다") {
                resultActions
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.data").value(expectedResult))

                verify(exactly = 1) { loginMemberUseCase.login(any()) }
            }
        }
    }

    Given("로그인(비정상 케이스) 요청을 보내는 상황에서") {
        val expectedResult = "accessToken"
        every { loginMemberUseCase.login(any()) } returns expectedResult

        mockMvc = MockMvcBuilders.standaloneSetup(
            LoginMemberController(loginMemberUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("필요한 데이터를 누락시켜, 로그인 시도를 하면") {
            val loginMemberCommand = LoginMemberCommand(
                phoneNumber = "",
                password = ""
            )

            val resultActions = mockMvc.perform(
                post("/api/login")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(loginMemberCommand)))
                .andDo(print())

            Then("400번 에러가 발생해야 한다") {
                resultActions
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.data").value(null))
                    .andExpect(jsonPath("$.meta.message").value("필수 입력 값을 누락했습니다."))

                verify(exactly = 0) { loginMemberUseCase.login(any()) }
            }
        }
    }
})