package com.payhere.recruit.homework.controller

import com.payhere.recruit.homework.common.dto.response.BaseResponse
import com.payhere.recruit.homework.service.RemoveProductUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Controller class for removing a product.
 *
 * @property removeProductUseCase The RemoveProductUseCase instance for removing products.
 */
@RestController
class RemoveProductController(
    private val removeProductUseCase: RemoveProductUseCase
) {

    /**
     * Handles DELETE requests to remove a product.
     *
     * @param productId The ID of the product to remove.
     * @return A ResponseEntity containing the response data.
     */
    @DeleteMapping("/api/products/{product_id}")
    fun removeProduct(@PathVariable("product_id") productId: Long) :
            ResponseEntity<BaseResponse<Any?>> {
        removeProductUseCase.removeProduct(productId)

        return ResponseEntity.ok(BaseResponse.ok())
    }
}