package com.payhere.recruit.homework.controller

import com.payhere.recruit.homework.common.dto.response.BaseResponse
import com.payhere.recruit.homework.service.LoadProductUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Controller class for loading product details and lists.
 *
 * @property loadProductUseCase The LoadProductUseCase instance for loading products.
 */
@RestController
class LoadProductController(
    private val loadProductUseCase: LoadProductUseCase
) {

    /**
     * Handles GET requests to load details of a specific product.
     *
     * @param productId The ID of the product to load details for.
     * @return A ResponseEntity containing the response data.
     */
    @GetMapping("/api/products/{product_id}")
    fun loadProductDetail(@PathVariable("product_id") productId: Long) :
            ResponseEntity<BaseResponse<Any?>> {

        val findProductResponse = loadProductUseCase.loadProductDetail(productId)
        if (findProductResponse.isPresent) return ResponseEntity.ok(BaseResponse.ok(findProductResponse))

        return ResponseEntity.noContent()
            .build()
    }

    /**
     * Handles GET requests to load a list of products.
     *
     * @param prevProductId The ID of the last product in the previous page.
     * @param searchKeyword The keyword to search for in product names.
     * @return A ResponseEntity containing the response data.
     */
    @GetMapping("/api/products")
    fun loadProductList(@RequestParam("prevProductId") prevProductId: Long?,
                        @RequestParam("searchKeyword") searchKeyword: String?) =
        ResponseEntity.ok()
            .body(
                BaseResponse.ok(loadProductUseCase.loadProductList(prevProductId, searchKeyword))
            )
}