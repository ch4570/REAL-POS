package com.payhere.recruit.homework.common.exception

import com.payhere.recruit.homework.common.dto.BaseResponse
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 예외를 전역적으로 처리하기 위한 전역 컨트롤러 어드바이스 클래스입니다.
 */
@RestControllerAdvice
class GlobalCatcher {

    /**
     * CustomException을 처리하고 적절한 오류 세부 정보를 포함하는 ResponseEntity를 반환합니다.
     *
     * @param customException 처리할 CustomException입니다.
     * @return 오류 세부 정보가 포함된 ResponseEntity입니다.
     */
    @ExceptionHandler(CustomException::class)
    protected fun handleCustomException(customException: CustomException): ResponseEntity<BaseResponse<Any?>> {
        val errorCode = customException.errorCode
        val errorResponse = BaseResponse.customExceptionResponse(
            code = errorCode.status.value(),
            message = errorCode.message
        )

        return ResponseEntity.status(errorCode.status)
            .body(errorResponse)
    }

    /**
     * 다른 모든 예외를 처리하고 내부 서버 오류 ResponseEntity를 반환합니다.
     *
     * @param exception 처리할 예외입니다.
     * @return 내부 서버 오류를 나타내는 ResponseEntity입니다.
     */
    @ExceptionHandler(Exception::class)
    protected fun handleNormalException(exception: Exception) =
        ResponseEntity.internalServerError()
            .body(BaseResponse.normalExceptionResponse())

    /**
     * 유효하지 않은 입력 예외를 처리하고 잘못된 요청을 나타내는 ResponseEntity를 반환합니다.
     *
     * @return 유효하지 않은 입력으로 인한 잘못된 요청을 나타내는 ResponseEntity입니다.
     */
    @ExceptionHandler(HttpMessageNotReadableException::class, MethodArgumentNotValidException::class)
    protected fun handleInvalidInputException() =
        ResponseEntity.badRequest()
            .body(BaseResponse.invalidInputExceptionResponse())
}
