package com.payhere.recruit.homework.controller

import com.payhere.recruit.homework.common.dto.request.CreateMemberCommand
import com.payhere.recruit.homework.common.dto.response.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.service.CreateMemberUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateMemberController(
    private val createMemberUseCase: CreateMemberUseCase
) {

    @PostMapping("/api/members")
    fun createMember(@RequestBody @Valid command: CreateMemberCommand, bindingResult: BindingResult):
            ResponseEntity<BaseResponse<Long>> {

        if (bindingResult.hasErrors()) throw CustomException(INVALID_INPUT_DATA)
        val savedMemberId = createMemberUseCase.createUser(command)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse.created(savedMemberId))
    }
}