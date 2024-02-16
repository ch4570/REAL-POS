package com.payletter.recruit.homework.common.dto.response

data class BaseResponse<T>(
    val meta: Meta,
    val data: T?
) {

    companion object {
        fun <T> ok(data: T) : BaseResponse<T> {
            val meta = Meta(code = 200, message = "OK")
            return BaseResponse(meta, data)
        }

        fun ok() : BaseResponse<Any?> {
            val meta = Meta(code = 200, message = "OK")
            return BaseResponse(meta, null)
        }

        fun <T> created(data: T) : BaseResponse<T> {
            val meta = Meta(code = 201, message = "CREATED")
            return BaseResponse(meta, data)
        }

        fun invalidInputExceptionResponse() : BaseResponse<Any?> {
            val meta = Meta(code = 400, message = "잘못된 입력 값을 전송했습니다.")
            return BaseResponse(meta, null)
        }

        fun customExceptionResponse(code: Int, message: String) : BaseResponse<Any?> {
            val meta = Meta(code = code, message = message)
            return BaseResponse(meta, null)
        }

        fun normalExceptionResponse() : BaseResponse<Any?> {
            val meta = Meta(code = 500, message = "처리 중 에러가 발생했습니다.")
            return BaseResponse(meta, null)
        }
    }

}

data class Meta(
    val code: Int,
    val message: String
)