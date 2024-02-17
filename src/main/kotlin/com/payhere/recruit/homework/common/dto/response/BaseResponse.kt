package com.payhere.recruit.homework.common.dto.response

/**
 * Data class representing the meta information of a response.
 *
 * @property code The status code of the response.
 * @property message The message describing the response status.
 */
data class Meta(
    val code: Int,
    val message: String
)

/**
 * Data class representing a base response.
 *
 * @property meta The meta information of the response.
 * @property data The data associated with the response.
 */
data class BaseResponse<T>(
    val meta: Meta,
    val data: T?
) {
    companion object {
        /**
         * Creates a successful response with the provided data.
         */
        fun <T> ok(data: T): BaseResponse<T> {
            val meta = Meta(code = 200, message = "OK")
            return BaseResponse(meta, data)
        }

        /**
         * Creates a successful response without data.
         */
        fun ok(): BaseResponse<Any?> {
            val meta = Meta(code = 200, message = "OK")
            return BaseResponse(meta, null)
        }

        /**
         * Creates a response indicating that a resource has been created, with the provided data.
         */
        fun <T> created(data: T): BaseResponse<T> {
            val meta = Meta(code = 201, message = "CREATED")
            return BaseResponse(meta, data)
        }

        /**
         * Creates a response for an invalid input exception.
         */
        fun invalidInputExceptionResponse(): BaseResponse<Any?> {
            val meta = Meta(code = 400, message = "잘못된 입력 값을 전송했습니다.")
            return BaseResponse(meta, null)
        }

        /**
         * Creates a response for a custom exception with the provided code and message.
         */
        fun customExceptionResponse(code: Int, message: String): BaseResponse<Any?> {
            val meta = Meta(code = code, message = message)
            return BaseResponse(meta, null)
        }

        /**
         * Creates a response for a normal exception.
         */
        fun normalExceptionResponse(): BaseResponse<Any?> {
            val meta = Meta(code = 500, message = "처리 중 에러가 발생했습니다.")
            return BaseResponse(meta, null)
        }
    }
}