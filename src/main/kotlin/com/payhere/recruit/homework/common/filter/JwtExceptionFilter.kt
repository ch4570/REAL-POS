package com.payhere.recruit.homework.common.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.payhere.recruit.homework.common.dto.BaseResponse
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * JWT 예외를 처리하고 적절한 오류 응답을 제공하는 컴포넌트 클래스입니다.
 *
 * @property objectMapper JSON 직렬화를 위한 ObjectMapper 인스턴스입니다.
 */
@Component
class JwtExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    /**
     * 수신된 요청을 필터링하고 JWT 예외를 처리합니다.
     *
     * @param request 수신된 요청을 나타내는 HttpServletRequest 객체입니다.
     * @param response 응답을 나타내는 HttpServletResponse 객체입니다.
     * @param filterChain 체인에서 다음 필터를 호출하기 위한 FilterChain 객체입니다.
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (ex: JwtException) {
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = Charsets.UTF_8.toString()

            objectMapper.writeValue(response.writer,
                BaseResponse.customExceptionResponse(
                    HttpStatus.UNAUTHORIZED.value(),
                    ex.message!!
                )
            )
        }
    }
}
