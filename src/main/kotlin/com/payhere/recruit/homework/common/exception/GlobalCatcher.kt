package com.payhere.recruit.homework.common.exception

import com.payhere.recruit.homework.common.dto.response.BaseResponse
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Global controller advice class for handling exceptions globally.
 */
@RestControllerAdvice
class GlobalCatcher {

    /**
     * Handles CustomException and returns an appropriate ResponseEntity with error details.
     *
     * @param customException The CustomException to handle.
     * @return A ResponseEntity containing error details.
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
     * Handles any other Exception and returns an internal server error ResponseEntity.
     *
     * @param exception The Exception to handle.
     * @return A ResponseEntity indicating an internal server error.
     */
    @ExceptionHandler(Exception::class)
    protected fun handleNormalException(exception: Exception) =
        ResponseEntity.internalServerError()
            .body(BaseResponse.normalExceptionResponse())

    /**
     * Handles invalid input exceptions and returns a bad request ResponseEntity.
     *
     * @return A ResponseEntity indicating a bad request due to invalid input.
     */
    @ExceptionHandler(HttpMessageNotReadableException::class, MethodArgumentNotValidException::class)
    protected fun handleInvalidInputException() =
        ResponseEntity.badRequest()
            .body(BaseResponse.invalidInputExceptionResponse())
}
