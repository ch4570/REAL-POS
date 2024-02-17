package com.payhere.recruit.homework.controller

import com.payhere.recruit.homework.common.dto.request.ModifyProductCommand
import com.payhere.recruit.homework.common.dto.response.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode
import com.payhere.recruit.homework.service.ModifyProductUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ModifyProductController(
    private val modifyProductUseCase: ModifyProductUseCase
) {

    @PatchMapping("/api/products/{product_id}")
    fun modifyProduct(@PathVariable("product_id") productId: Long,
                      @RequestBody @Valid modifyProductCommand: ModifyProductCommand,
                      bindingResult: BindingResult) : ResponseEntity<BaseResponse<Any?>> {

        if (bindingResult.hasErrors()) throw CustomException(ErrorCode.INVALID_INPUT_DATA)
        modifyProductUseCase.modifyProduct(productId, modifyProductCommand)

        return ResponseEntity.ok(BaseResponse.ok())
    }
}