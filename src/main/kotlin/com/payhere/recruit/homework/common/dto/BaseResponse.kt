package com.payhere.recruit.homework.common.dto

/**
 * 응답의 메타 정보를 나타내는 데이터 클래스입니다.
 *
 * @property code 응답의 상태 코드입니다.
 * @property message 응답 상태를 설명하는 메시지입니다.
 */
data class Meta(
    val code: Int,
    val message: String
)

/**
 * 기본 응답을 나타내는 데이터 클래스입니다.
 *
 * @property meta 응답의 메타 정보입니다.
 * @property data 응답과 관련된 데이터입니다.
 */
data class BaseResponse<T>(
    val meta: Meta,
    val data: T?
) {
    companion object {
        /**
         * 제공된 데이터로 성공적인 응답을 생성합니다.
         */
        fun <T> ok(data: T): BaseResponse<T> {
            val meta = Meta(code = 200, message = "OK")
            return BaseResponse(meta, data)
        }

        /**
         * 데이터 없이 성공적인 응답을 생성합니다.
         */
        fun ok(): BaseResponse<Any?> {
            val meta = Meta(code = 200, message = "OK")
            return BaseResponse(meta, null)
        }

        /**
         * 리소스가 생성되었음을 나타내는 응답을 제공된 데이터로 생성합니다.
         */
        fun <T> created(data: T): BaseResponse<T> {
            val meta = Meta(code = 201, message = "CREATED")
            return BaseResponse(meta, data)
        }

        /**
         * 잘못된 입력 예외에 대한 응답을 생성합니다.
         */
        fun invalidInputExceptionResponse(): BaseResponse<Any?> {
            val meta = Meta(code = 400, message = "잘못된 입력 값을 전송했습니다.")
            return BaseResponse(meta, null)
        }

        /**
         * 제공된 코드와 메시지로 사용자 정의 예외에 대한 응답을 생성합니다.
         */
        fun customExceptionResponse(code: Int, message: String): BaseResponse<Any?> {
            val meta = Meta(code = code, message = message)
            return BaseResponse(meta, null)
        }

        /**
         * 일반적인 예외에 대한 응답을 생성합니다.
         */
        fun normalExceptionResponse(): BaseResponse<Any?> {
            val meta = Meta(code = 500, message = "처리 중 에러가 발생했습니다.")
            return BaseResponse(meta, null)
        }
    }
}