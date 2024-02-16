package com.payletter.recruit.homework.common.exception

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()