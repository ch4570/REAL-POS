package com.payhere.recruit.homework.controller

import com.payhere.recruit.homework.common.dto.request.LoginMemberCommand
import com.payhere.recruit.homework.common.dto.response.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.service.impl.LoginMemberService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * Controller class for logging in a member.
 *
 * @property loginMemberService The LoginMemberService instance for member login.
 */
@RestController
class LoginMemberController(
    private val loginMemberService: LoginMemberService,
) {

    /**
     * Handles POST requests to log in a member.
     *
     * @param loginMemberCommand The command object containing login credentials.
     * @param bindingResult The BindingResult object for validation errors.
     * @return A ResponseEntity containing the response data.
     * @throws CustomException if there are validation errors.
     */
    @PostMapping("/api/login")
    fun loginMember(@RequestBody @Valid loginMemberCommand: LoginMemberCommand, bindingResult: BindingResult) :
            ResponseEntity<BaseResponse<String>> {

        if (bindingResult.hasErrors()) throw CustomException(INVALID_INPUT_DATA)
        val jwtToken = loginMemberService.login(loginMemberCommand)

        return ResponseEntity.ok(BaseResponse.ok(jwtToken))
    }
}