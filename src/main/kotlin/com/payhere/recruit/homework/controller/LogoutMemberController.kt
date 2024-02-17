package com.payhere.recruit.homework.controller

import com.payhere.recruit.homework.common.dto.request.LogoutMemberCommand
import com.payhere.recruit.homework.common.dto.response.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.service.LogoutMemberUseCase
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LogoutMemberController(
    private val logoutMemberUseCase: LogoutMemberUseCase
) {

    @PostMapping("/api/logout")
    fun logoutMember(@RequestBody logoutMemberCommand: LogoutMemberCommand, bindingResult: BindingResult) :
            ResponseEntity<BaseResponse<Any?>> {
        if (bindingResult.hasErrors()) throw CustomException(INVALID_INPUT_DATA)

        logoutMemberUseCase.logout(logoutMemberCommand)
        return ResponseEntity.ok(BaseResponse.ok())
    }
}