package com.payletter.recruit.homework.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class ErrorCode(
    val status: HttpStatus,
    val serial: String,
    val message: String
) {

    INVALID_INPUT_DATA(BAD_REQUEST, "ERROR_01", "필수 입력 값을 누락했습니다."),
    NOT_PRESENT_MEMBER(INTERNAL_SERVER_ERROR, "ERROR_02", "조회 시도한 회원은 존재하지 않는 회원입니다."),
    INVALID_LOGIN_ID_OR_PASSWORD(INTERNAL_SERVER_ERROR, "ERROR_03", "아이디 또는 비밀번호가 일치하지 않습니다."),
    IS_ALREADY_PRESENT_MEMBER(INTERNAL_SERVER_ERROR, "ERROR_04", "이미 존재하는 회원입니다.")
}