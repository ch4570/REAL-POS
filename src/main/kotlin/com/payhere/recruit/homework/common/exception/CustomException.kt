package com.payhere.recruit.homework.common.exception

/**
 * 특정 오류 코드를 가진 예외를 나타내는 사용자 정의 예외 클래스입니다.
 *
 * @property errorCode 이 예외와 관련된 예외 케이스 정의를 가지고 있는 열거 클래스입니다.
 */
class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()