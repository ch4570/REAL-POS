package com.payletter.recruit.homework.controller

import com.payletter.recruit.homework.common.dto.request.LogoutMemberCommand
import com.payletter.recruit.homework.common.dto.response.BaseResponse
import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.service.LogoutMemberUseCase
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.util.StringUtils
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
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
        return ResponseEntity.ok()
            .body(BaseResponse.ok())
    }
}