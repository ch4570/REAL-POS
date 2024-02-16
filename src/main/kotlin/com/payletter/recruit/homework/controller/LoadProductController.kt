package com.payletter.recruit.homework.controller

import com.payletter.recruit.homework.common.dto.response.BaseResponse
import com.payletter.recruit.homework.service.LoadProductUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadProductController(
    private val loadProductUseCase: LoadProductUseCase
) {

    @GetMapping("/api/products/{product_id}")
    fun loadProductDetail(@PathVariable("product_id") productId: Long) :
            ResponseEntity<BaseResponse<Any?>> {

        val findProductResponse = loadProductUseCase.loadProductDetail(productId)
        if (findProductResponse.isPresent) return ResponseEntity.ok(BaseResponse.ok(findProductResponse))

        return ResponseEntity.noContent()
            .build()
    }

    @GetMapping("/api/products")
    fun loadProductList(@RequestParam("prevProductId") prevProductId: Long?,
                        @RequestParam("searchKeyword") searchKeyword: String?) =
        ResponseEntity.ok()
            .body(
                BaseResponse.ok(loadProductUseCase.loadProductList(prevProductId, searchKeyword))
            )
}