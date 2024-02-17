package com.payhere.recruit.homework.common.config


import com.payhere.recruit.homework.common.filter.JwtAuthenticationFilter
import com.payhere.recruit.homework.common.filter.JwtExceptionFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
/**
 * 보안 관련 설정을 위한 설정 클래스입니다.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtExceptionFilter: JwtExceptionFilter
) {

    /**
     * 보안 필터 체인을 정의합니다.
     *
     * @param http 보안 설정을 구성하는 HttpSecurity 객체입니다.
     * @return 보안 필터 체인을 정의하는 SecurityFilterChain 인스턴스 입니다.
     */
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { request ->
                request.requestMatchers(HttpMethod.POST, "/api/members/**").permitAll()
                request.requestMatchers(HttpMethod.POST, "/api/login/**").permitAll()
                request.requestMatchers(HttpMethod.POST, "/api/logout/**").permitAll()
                request.requestMatchers(HttpMethod.GET, "/api/token/**")
                request.anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter::class.java)
            .build()
    }
}