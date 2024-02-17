package com.payhere.recruit.homework.member.controller

import com.payhere.recruit.homework.member.domain.dto.request.LogoutMemberCommand
import com.payhere.recruit.homework.common.dto.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.member.service.LogoutMemberUseCase
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * 회원 로그아웃을 처리하는 컨트롤러 클래스입니다.
 *
 * @property logoutMemberUseCase 회원 로그아웃을 위한 LogoutMemberUseCase 인스턴스입니다.
 */
@RestController
class LogoutMemberController(
    private val logoutMemberUseCase: LogoutMemberUseCase
) {

    /**
     * POST 요청을 처리하여 회원을 로그아웃합니다.
     *
     * @param logoutMemberCommand 로그아웃 세부 정보를 포함하는 커맨드 객체입니다.
     * @param bindingResult 유효성 검사 오류를 포함하는 BindingResult 객체입니다.
     * @return 응답 데이터를 포함하는 ResponseEntity입니다.
     * @throws CustomException 유효성 검사 오류가 있는 경우 발생합니다.
     */
    @PostMapping("/api/logout")
    fun logoutMember(@RequestBody logoutMemberCommand: LogoutMemberCommand, bindingResult: BindingResult) :
            ResponseEntity<BaseResponse<Any?>> {
        if (bindingResult.hasErrors()) throw CustomException(INVALID_INPUT_DATA)

        logoutMemberUseCase.logout(logoutMemberCommand)
        return ResponseEntity.ok(BaseResponse.ok())
    }
}
