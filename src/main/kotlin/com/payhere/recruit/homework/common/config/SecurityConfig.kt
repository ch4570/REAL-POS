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
 * Configuration class for security-related settings.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtExceptionFilter: JwtExceptionFilter
) {

    /**
     * Defines the security filter chain.
     *
     * @param http The HttpSecurity object to configure security settings.
     * @return The SecurityFilterChain instance defining the security filter chain.
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