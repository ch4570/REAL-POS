package com.payletter.recruit.homework.common.filter

import com.payletter.recruit.homework.common.util.EncryptUtil
import com.payletter.recruit.homework.common.util.JwtUtil
import com.payletter.recruit.homework.service.LoadMemberUseCase
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

@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val loadMemberUseCase: LoadMemberUseCase,
    private val encryptUtil: EncryptUtil
) : OncePerRequestFilter() {
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

        val findMember = loadMemberUseCase.loadUserByPhoneNumber(encryptUtil.encryptString(
            jwtUtil.extractPhoneNumber(token))
        )

        val securityMember = UsernamePasswordAuthenticationToken(
            findMember, null, listOf()
        )

        SecurityContextHolder.getContext().authentication = securityMember

        filterChain.doFilter(request, response)
    }


}