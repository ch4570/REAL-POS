package com.payhere.recruit.homework.member.controller

import com.payhere.recruit.homework.member.domain.dto.request.CreateMemberCommand
import com.payhere.recruit.homework.common.dto.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.member.service.CreateMemberUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * 새로운 회원을 생성하는 컨트롤러 클래스입니다.
 *
 * @property createMemberUseCase 회원을 생성하기 위한 CreateMemberUseCase 인스턴스입니다.
 */
@RestController
class CreateMemberController(
    private val createMemberUseCase: CreateMemberUseCase
) {

    /**
     * POST 요청을 처리하여 새로운 회원을 생성합니다.
     *
     * @param command 회원 생성에 필요한 데이터를 포함하는 커맨드 객체입니다.
     * @param bindingResult 유효성 검사 오류를 포함하는 BindingResult 객체입니다.
     * @return 응답 데이터를 포함하는 ResponseEntity입니다.
     * @throws CustomException 유효성 검사 오류가 있는 경우 발생합니다.
     */
    @PostMapping("/api/members")
    fun createMember(@RequestBody @Valid command: CreateMemberCommand, bindingResult: BindingResult):
            ResponseEntity<BaseResponse<Long>> {

        if (bindingResult.hasErrors()) throw CustomException(INVALID_INPUT_DATA)
        val savedMemberId = createMemberUseCase.createUser(command)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse.created(savedMemberId))
    }
}
