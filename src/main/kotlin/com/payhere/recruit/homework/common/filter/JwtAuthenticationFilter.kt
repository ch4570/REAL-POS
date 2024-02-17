package com.payhere.recruit.homework.common.filter

import com.payhere.recruit.homework.common.util.JwtUtil
import com.payhere.recruit.homework.member.service.LoadMemberUseCase
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
 * 요청에서 JWT 토큰을 필터링하고 인증하는 컴포넌트 클래스입니다.
 *
 * @property jwtUtil 토큰 작업을 위한 JwtUtil 인스턴스입니다.
 * @property loadMemberUseCase 회원 세부 정보를 로드하기 위한 LoadMemberUseCase 인스턴스입니다.
 */
@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val loadMemberUseCase: LoadMemberUseCase,
) : OncePerRequestFilter() {

    /**
     * 요청에서 JWT 토큰을 필터링하고 인증합니다.
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

