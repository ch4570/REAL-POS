package com.payhere.recruit.homework.common.exception

/**
 * Custom exception class representing an exception with a specific error code.
 *
 * @property errorCode The ErrorCode associated with this exception.
 */
class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()