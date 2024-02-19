package com.payhere.recruit.homework.member.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.payhere.recruit.homework.common.exception.GlobalCatcher
import com.payhere.recruit.homework.member.domain.dto.request.LogoutMemberCommand
import com.payhere.recruit.homework.member.service.LogoutMemberUseCase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@SpringBootTest
internal class LogoutMemberControllerTest(
    private val objectMapper: ObjectMapper
) : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val logoutMemberUseCase = mockk<LogoutMemberUseCase>()
    lateinit var mockMvc: MockMvc

    Given("로그아웃(정상 케이스) 요청을 하는 상황에서") {
        every { logoutMemberUseCase.logout(any()) } just Runs

        mockMvc = MockMvcBuilders.standaloneSetup(
            LogoutMemberController(logoutMemberUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("필요한 데이터를 모두 담아, 로그아웃 시도를 하면") {
            val logoutMemberCommand = LogoutMemberCommand(accessToken = "accessToken")

            val resultActions = mockMvc.perform(
                post("/api/logout")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(logoutMemberCommand)))
                .andDo(print())

            Then("요청이 정상적으로 처리되어야 한다") {
                resultActions
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.data").value(null))

                verify(exactly = 1) { logoutMemberUseCase.logout(any()) }
            }
        }
    }

    Given("로그아웃(비정상 케이스) 요청을 하는 상황에서") {
        every { logoutMemberUseCase.logout(any()) } just Runs

        mockMvc = MockMvcBuilders.standaloneSetup(
            LogoutMemberController(logoutMemberUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("필요한 데이터를 누락시켜, 로그아웃 시도를 하면") {
            val logoutMemberCommand = LogoutMemberCommand(accessToken = "")

            val resultActions = mockMvc.perform(
                post("/api/logout")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(logoutMemberCommand)))
                .andDo(print())

            Then("400번 에러가 발생해야 한다") {
                resultActions
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.data").value(null))
                    .andExpect(jsonPath("$.meta.message").value("필수 입력 값을 누락했습니다."))

                verify(exactly = 0) { logoutMemberUseCase.logout(any()) }
            }
        }
    }
})