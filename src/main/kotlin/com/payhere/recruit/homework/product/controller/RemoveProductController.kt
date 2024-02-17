package com.payhere.recruit.homework.product.controller

import com.payhere.recruit.homework.common.dto.BaseResponse
import com.payhere.recruit.homework.product.service.RemoveProductUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * 제품을 제거하는 컨트롤러 클래스입니다.
 *
 * @property removeProductUseCase 제품을 제거하는 데 사용되는 RemoveProductUseCase 인스턴스입니다.
 */
@RestController
class RemoveProductController(
    private val removeProductUseCase: RemoveProductUseCase
) {

    /**
     * 제품을 제거하기 위해 DELETE 요청을 처리합니다.
     *
     * @param productId 제거할 제품의 ID입니다.
     * @return 응답 데이터를 포함하는 ResponseEntity입니다.
     */
    @DeleteMapping("/api/products/{product_id}")
    fun removeProduct(@PathVariable("product_id") productId: Long) :
            ResponseEntity<BaseResponse<Any?>> {
        removeProductUseCase.removeProduct(productId)

        return ResponseEntity.ok(BaseResponse.ok())
    }
}
