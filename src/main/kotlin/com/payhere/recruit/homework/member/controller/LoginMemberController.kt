package com.payhere.recruit.homework.member.controller

import com.payhere.recruit.homework.member.domain.dto.request.LoginMemberCommand
import com.payhere.recruit.homework.common.dto.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.member.service.LoginMemberUseCase
import com.payhere.recruit.homework.member.service.impl.LoginMemberService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * 회원 로그인을 처리하는 컨트롤러 클래스입니다.
 *
 * @property loginMemberUseCase 회원 로그인을 위한 loginMemberUseCase 인스턴스입니다.
 */
@RestController
class LoginMemberController(
    private val loginMemberUseCase: LoginMemberUseCase
) {

    /**
     * POST 요청을 처리하여 회원 로그인을 합니다.
     *
     * @param loginMemberCommand 로그인 자격 증명을 포함하는 커맨드 객체입니다.
     * @param bindingResult 유효성 검사 오류를 포함하는 BindingResult 객체입니다.
     * @return 응답 데이터를 포함하는 ResponseEntity입니다.
     * @throws CustomException 유효성 검사 오류가 있는 경우 발생합니다.
     */
    @PostMapping("/api/login")
    fun loginMember(@RequestBody @Valid loginMemberCommand: LoginMemberCommand, bindingResult: BindingResult) :
            ResponseEntity<BaseResponse<String>> {

        if (bindingResult.hasErrors()) throw CustomException(INVALID_INPUT_DATA)
        val jwtToken = loginMemberUseCase.login(loginMemberCommand)

        return ResponseEntity.ok(BaseResponse.ok(jwtToken))
    }
}