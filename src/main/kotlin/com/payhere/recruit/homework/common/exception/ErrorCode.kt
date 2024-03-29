package com.payhere.recruit.homework.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

/**
 * HTTP 상태 코드 및 해당하는 메시지와 함께 오류 코드를 나타내는 열거형 클래스입니다.
 */
enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {

    INVALID_INPUT_DATA(BAD_REQUEST, "필수 입력 값을 누락했습니다."),
    NOT_EXISTS_MEMBER(INTERNAL_SERVER_ERROR, "조회 시도한 회원은 존재하지 않는 회원입니다."),
    INVALID_LOGIN_ID_OR_PASSWORD(INTERNAL_SERVER_ERROR, "아이디 또는 비밀번호가 일치하지 않습니다."),
    IS_ALREADY_EXISTS_MEMBER(INTERNAL_SERVER_ERROR, "이미 존재하는 회원입니다."),
    IS_ALREADY_EXISTS_PRODUCT(INTERNAL_SERVER_ERROR, "이미 존재하는 상품입니다."),
    NOT_EXISTS_PRODUCT(INTERNAL_SERVER_ERROR, "조회 시도한 상품은 존재하지 않는 상품입니다.")
}
