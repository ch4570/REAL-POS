package com.payhere.recruit.homework.common.filter

import com.payhere.recruit.homework.common.util.JwtUtil
import com.payhere.recruit.homework.service.LoadMemberUseCase
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Component class for filtering and authenticating JWT tokens in requests.
 *
 * @property jwtUtil The JwtUtil instance for token operations.
 * @property loadMemberUseCase The LoadMemberUseCase instance for loading member details.
 */
@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val loadMemberUseCase: LoadMemberUseCase,
) : OncePerRequestFilter() {

    /**
     * Filters and authenticates JWT tokens in incoming requests.
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

        val token = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (!StringUtils.hasText(token)) {
            doFilter(request, response, filterChain)
            return
        }

        val isAuthorized = jwtUtil.verifyToken(token)

        if (!isAuthorized) throw JwtException("토큰이 유효하지 않습니다.")

        val findMember = loadMemberUseCase.loadUserByPhoneNumber(jwtUtil.extractPhoneNumber(token))

        val securityMember = UsernamePasswordAuthenticationToken(
            findMember, null, listOf()
        )

        SecurityContextHolder.getContext().authentication = securityMember

        filterChain.doFilter(request, response)
    }
}
