package com.payhere.recruit.homework.common.exception

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()