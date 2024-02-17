package com.payhere.recruit.homework.controller

import com.payhere.recruit.homework.common.dto.request.CreateProductCommand
import com.payhere.recruit.homework.common.dto.response.BaseResponse
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.service.CreateProductUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateProductController(
    private val createProductUseCase: CreateProductUseCase
) {

    @PostMapping("/api/products")
    fun createProduct(@RequestBody @Valid command: CreateProductCommand, bindingResult: BindingResult) :
            ResponseEntity<BaseResponse<Long>> {
        if (bindingResult.hasErrors()) {
            println(bindingResult.fieldError)
            throw CustomException(INVALID_INPUT_DATA)
        }

        val savedProductId = createProductUseCase.createProduct(command)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse.created(savedProductId))
    }
}