package com.payhere.recruit.homework.common.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.payhere.recruit.homework.common.dto.response.BaseResponse
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

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