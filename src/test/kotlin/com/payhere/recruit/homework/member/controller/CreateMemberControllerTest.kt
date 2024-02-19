package com.payhere.recruit.homework.member.controller

import com.appmattus.kotlinfixture.Fixture
import com.fasterxml.jackson.databind.ObjectMapper
import com.payhere.recruit.homework.common.exception.GlobalCatcher
import com.payhere.recruit.homework.member.domain.dto.request.CreateMemberCommand
import com.payhere.recruit.homework.member.service.CreateMemberUseCase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@SpringBootTest
@Import(Fixture::class)
internal class CreateMemberControllerTest(
    private val fixture: Fixture,
    private val objectMapper: ObjectMapper
) : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val createMemberUseCase = mockk<CreateMemberUseCase>()
    lateinit var mockMvc: MockMvc

    Given("신규 회원 등록(정상 케이스) 요청을 보내는 상황에서") {
        val expectedResult = 1L
        every { createMemberUseCase.createUser(any()) } returns expectedResult

        mockMvc = MockMvcBuilders.standaloneSetup(
            CreateMemberController(createMemberUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("필요한 데이터를 모두 담아, 신규 회원 등록 요청을 보내면") {
            val createMemberCommand = CreateMemberCommand(
                phoneNumber = "010-1111-1111",
                password = "payhere12345!!"
            )

            val resultActions = mockMvc.perform(
                post("/api/members")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createMemberCommand)))
                .andDo(print())

            Then("요청이 정상적으로 처리되어야 한다") {
                resultActions
                    .andExpect(status().isCreated)
                    .andExpect(jsonPath("$.data").value(expectedResult))

                verify(exactly = 1) { createMemberUseCase.createUser(any()) }
            }
        }
    }

    Given("신규 회원 등록(비정상 케이스) 요청을 보내는 상황에서") {
        every { createMemberUseCase.createUser(any()) } returns 1L

        mockMvc = MockMvcBuilders.standaloneSetup(
            CreateMemberController(createMemberUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("필요한 데이터를 누락시켜, 신규 회원 등록 요청을 보내면") {
            val createMemberCommand = fixture<CreateMemberCommand>()

            val resultActions = mockMvc.perform(
                post("/api/members")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createMemberCommand)))
                .andDo(print())

            Then("400번 에러가 발생해야 한다") {
                resultActions
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.data").value(null))
                    .andExpect(jsonPath("$.meta.message").value("필수 입력 값을 누락했습니다."))

                verify(exactly = 0) { createMemberUseCase.createUser(any()) }
            }
        }
    }
})