package com.payhere.recruit.homework.product.controller

import com.payhere.recruit.homework.product.domain.dto.request.ModifyProductCommand
import com.payhere.recruit.homework.common.dto.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode
import com.payhere.recruit.homework.product.service.ModifyProductUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * 제품을 수정하는 컨트롤러 클래스입니다.
 *
 * @property modifyProductUseCase 제품을 수정하는 데 사용되는 ModifyProductUseCase 인스턴스입니다.
 */
@RestController
class ModifyProductController(
    private val modifyProductUseCase: ModifyProductUseCase
) {

    /**
     * 제품을 수정하기 위해 PATCH 요청을 처리합니다.
     *
     * @param productId 수정할 제품의 ID입니다.
     * @param modifyProductCommand 제품을 수정하는 데 필요한 데이터를 포함하는 명령 객체입니다.
     * @param bindingResult 유효성 검사 오류를 포함하는 BindingResult 객체입니다.
     * @return 응답 데이터를 포함하는 ResponseEntity입니다.
     * @throws CustomException 유효성 검사 오류가 있는 경우 발생합니다.
     */
    @PatchMapping("/api/products/{product_id}")
    fun modifyProduct(@PathVariable("product_id") productId: Long,
                      @RequestBody @Valid modifyProductCommand: ModifyProductCommand,
                      bindingResult: BindingResult) : ResponseEntity<BaseResponse<Any?>> {

        if (bindingResult.hasErrors()) throw CustomException(ErrorCode.INVALID_INPUT_DATA)
        modifyProductUseCase.modifyProduct(productId, modifyProductCommand)

        return ResponseEntity.ok(BaseResponse.ok())
    }
}
