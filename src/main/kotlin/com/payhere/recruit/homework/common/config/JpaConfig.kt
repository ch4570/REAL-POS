package com.payhere.recruit.homework.common.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

/**
 * JPA 관련 설정 및 Bean을 정의하는 설정 클래스입니다.
 */
@Configuration
@EnableJpaAuditing
class JpaConfig(
    @PersistenceContext
    private val entityManager: EntityManager
) {

    /**
     * JPAQueryFactory 인스턴스를 생성하기 위한 빈을 정의합니다.
     *
     * @return 제공된 EntityManager를 사용하여 생성된 JPAQueryFactory 인스턴스 입니다.
     */
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory = JPAQueryFactory(entityManager)
}