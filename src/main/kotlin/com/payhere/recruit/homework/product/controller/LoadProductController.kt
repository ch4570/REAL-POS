package com.payhere.recruit.homework.product.controller

import com.payhere.recruit.homework.common.dto.BaseResponse
import com.payhere.recruit.homework.product.service.LoadProductUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 제품 세부 정보 및 목록을 로드하는 컨트롤러 클래스입니다.
 *
 * @property loadProductUseCase 제품을 로드하는 데 사용되는 LoadProductUseCase 인스턴스입니다.
 */
@RestController
class LoadProductController(
    private val loadProductUseCase: LoadProductUseCase
) {

    /**
     * 특정 제품의 세부 정보를 로드하는 GET 요청을 처리합니다.
     *
     * @param productId 세부 정보를 로드할 제품의 ID입니다.
     * @return 응답 데이터를 포함하는 ResponseEntity입니다.
     */
    @GetMapping("/api/products/{product_id}")
    fun loadProductDetail(@PathVariable("product_id") productId: Long) :
            ResponseEntity<BaseResponse<Any?>> {

        val findProductResponse = loadProductUseCase.loadProductDetail(productId)
        if (findProductResponse.isPresent) return ResponseEntity.ok(BaseResponse.ok(findProductResponse.get()))

        return ResponseEntity.noContent()
            .build()
    }

    /**
     * 제품 목록을 로드하는 GET 요청을 처리합니다.
     *
     * @param prevProductId 이전 페이지의 마지막 제품 ID입니다.
     * @param searchKeyword 제품 이름에서 검색할 키워드입니다.
     * @return 응답 데이터를 포함하는 ResponseEntity입니다.
     */
    @GetMapping("/api/products")
    fun loadProductList(@RequestParam("prevProductId") prevProductId: Long?,
                        @RequestParam("searchKeyword") searchKeyword: String?) =
        ResponseEntity.ok()
            .body(
                BaseResponse.ok(loadProductUseCase.loadProductList(prevProductId, searchKeyword))
            )
}
