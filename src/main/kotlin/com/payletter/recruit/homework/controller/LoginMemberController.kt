package com.payletter.recruit.homework.controller

import com.payletter.recruit.homework.common.dto.request.LoginMemberCommand
import com.payletter.recruit.homework.common.dto.response.BaseResponse
import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.service.impl.LoginMemberService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginMemberController(
    private val loginMemberService: LoginMemberService,
) {

    @PostMapping("/api/login")
    fun loginMember(@RequestBody @Valid loginMemberCommand: LoginMemberCommand, bindingResult: BindingResult) :
            ResponseEntity<BaseResponse<String>> {

        if (bindingResult.hasErrors()) throw CustomException(INVALID_INPUT_DATA)
        val jwtToken = loginMemberService.login(loginMemberCommand)

        return ResponseEntity.ok()
            .body(BaseResponse.ok(jwtToken))
    }
}