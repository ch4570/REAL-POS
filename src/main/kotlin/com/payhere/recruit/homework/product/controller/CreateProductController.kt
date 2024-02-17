package com.payhere.recruit.homework.product.controller

import com.payhere.recruit.homework.product.domain.dto.request.CreateProductCommand
import com.payhere.recruit.homework.common.dto.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.product.service.CreateProductUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * 새 제품을 생성하는 컨트롤러 클래스입니다.
 *
 * @property createProductUseCase 제품을 생성하는 데 사용되는 CreateProductUseCase 인스턴스입니다.
 */
@RestController
class CreateProductController(
    private val createProductUseCase: CreateProductUseCase
) {

    /**
     * 새 제품을 생성하는 POST 요청을 처리합니다.
     *
     * @param command 제품을 생성하는 데 필요한 데이터를 포함하는 명령 객체입니다.
     * @param bindingResult 유효성 검사 오류를 포함하는 BindingResult 객체입니다.
     * @return 응답 데이터를 포함하는 ResponseEntity입니다.
     * @throws CustomException 유효성 검사 오류가 있는 경우 발생합니다.
     */
    @PostMapping("/api/products")
    fun createProduct(@RequestBody @Valid command: CreateProductCommand, bindingResult: BindingResult) :
            ResponseEntity<BaseResponse<Long>> {
        if (bindingResult.hasErrors()) {
            throw CustomException(INVALID_INPUT_DATA)
        }

        val savedProductId = createProductUseCase.createProduct(command)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse.created(savedProductId))
    }
}