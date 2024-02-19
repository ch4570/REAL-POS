package com.payhere.recruit.homework.product.controller

import com.payhere.recruit.homework.common.exception.GlobalCatcher
import com.payhere.recruit.homework.product.service.RemoveProductUseCase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
internal class RemoveProductControllerTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val removeProductUseCase = mockk<RemoveProductUseCase>()
    lateinit var mockMvc: MockMvc


    Given("상품을 삭제(정상 케이스) 요청을 하는 상황에서") {
        every { removeProductUseCase.removeProduct(any()) } just Runs

        mockMvc = MockMvcBuilders.standaloneSetup(
            RemoveProductController(removeProductUseCase)
        ).setControllerAdvice(GlobalCatcher())
        .build()

        When("삭제할 상품의 PK를 URL에 담아 전송하면") {
            val resultActions = mockMvc.perform(
                delete("/api/products/1")
                    .contentType(APPLICATION_JSON))
                .andDo(print())

            Then("요청이 정상적으로 처리 되어야 한다") {
                resultActions
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.data").value(null))

                verify(exactly = 1) { removeProductUseCase.removeProduct(any()) }
            }
        }
    }

    Given("상품을 삭제(비정상 케이스) 요청을 하는 상황에서") {
        every { removeProductUseCase.removeProduct(any()) }.throws(Exception())

        mockMvc = MockMvcBuilders.standaloneSetup(
            RemoveProductController(removeProductUseCase)
        ).setControllerAdvice(GlobalCatcher())
            .build()

        When("처리 중 예외가 발생하면") {
            val resultActions = mockMvc.perform(
                delete("/api/products/1")
                    .contentType(APPLICATION_JSON))
                .andDo(print())

            Then("500번 에러가 발생해야 한다") {
                resultActions
                    .andExpect(status().isInternalServerError)
                    .andExpect(jsonPath("$.data").value(null))
                    .andExpect(jsonPath("$.meta.message").value("처리 중 에러가 발생했습니다."))

                verify(exactly = 1) { removeProductUseCase.removeProduct(any()) }
            }
        }
    }
})