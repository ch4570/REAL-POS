package com.payletter.recruit.homework.controller

import com.payletter.recruit.homework.common.dto.request.CreateMemberCommand
import com.payletter.recruit.homework.common.dto.response.BaseResponse
import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.service.CreateMemberUseCase
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