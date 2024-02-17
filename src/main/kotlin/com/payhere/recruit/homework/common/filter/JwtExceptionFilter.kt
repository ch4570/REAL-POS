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

/**
 * Component class for handling JWT exceptions and providing appropriate error responses.
 *
 * @property objectMapper The ObjectMapper instance for JSON serialization.
 */
@Component
class JwtExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    /**
     * Filters incoming requests and handles JWT exceptions.
     *
     * @param request The HttpServletRequest object representing the incoming request.
     * @param response The HttpServletResponse object representing the response.
     * @param filterChain The FilterChain object for invoking the next filter in the chain.
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
