package com.payhere.recruit.homework.product.controller

import com.appmattus.kotlinfixture.Fixture
import com.fasterxml.jackson.databind.ObjectMapper
import com.payhere.recruit.homework.common.exception.GlobalCatcher
import com.payhere.recruit.homework.product.domain.dto.request.ModifyProductCommand
import com.payhere.recruit.homework.product.domain.entity.Size.*
import com.payhere.recruit.homework.product.service.ModifyProductUseCase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime

@SpringBootTest
@Import(Fixture::class)
internal class ModifyProductControllerTest(
    private val objectMapper: ObjectMapper,
    private val fixture: Fixture
) : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val modifyProductUseCase = mockk<ModifyProductUseCase>()
    lateinit var mockMvc: MockMvc

    Given("상품을 변경(정상 케이스) 요청을 하는 상황에서") {
        every { modifyProductUseCase.modifyProduct(any(), any()) } just Runs

        mockMvc = MockMvcBuilders.standaloneSetup(
            ModifyProductController(modifyProductUseCase)
        ).setControllerAdvice(GlobalCatcher())
        .build()

        When("필요한 데이터를 모두 담아, 상품 수정 요청을 보내면") {
            val modifyProductCommand = ModifyProductCommand(
                categoryId = 1L,
                price = 10000,
                cost = 20000,
                description = "달콤한 바닐라 라떼의 참맛을 느껴보세요",
                productName = "바닐라 라떼",
                barcode = "98239481038129038102",
                expirationDate = LocalDateTime.now(),
                productSize = Small
            )

            val resultActions = mockMvc.perform(
                patch("/api/products/1")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(modifyProductCommand)))
                .andDo(print())

            Then("요청이 정상적으로 처리 되어야한다") {
                resultActions
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.data").value(null))
                    .andExpect(jsonPath("$.meta.message").value("OK"))

                verify(exactly = 1) { modifyProductUseCase.modifyProduct(any(), any()) }
            }
        }
    }

    Given("상품을 변경(비정상 케이스) 요청을 하는 상황에서") {
        every { modifyProductUseCase.modifyProduct(any(), any()) } just Runs

        mockMvc = MockMvcBuilders.standaloneSetup(
            ModifyProductController(modifyProductUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("필요한 데이터를 누락시켜, 상품 수정 요청을 보내면") {
            val modifyProductCommand = fixture<ModifyProductCommand>()

            val resultActions = mockMvc.perform(
                patch("/api/products/1")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(modifyProductCommand)))
                .andDo(print())

            Then("요청이 정상적으로 처리 되어야한다") {
                resultActions
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.data").value(null))
                    .andExpect(jsonPath("$.meta.message").value("필수 입력 값을 누락했습니다."))

                verify(exactly = 0) { modifyProductUseCase.modifyProduct(any(), any()) }
            }
        }
    }

})