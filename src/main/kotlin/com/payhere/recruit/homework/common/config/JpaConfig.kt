package com.payhere.recruit.homework.common.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

/**
 * Configuration class for JPA-related settings and beans.
 */
@Configuration
@EnableJpaAuditing
class JpaConfig(
    @PersistenceContext
    private val entityManager: EntityManager
) {

    /**
     * Defines a bean for creating a JPAQueryFactory instance.
     *
     * @return A JPAQueryFactory instance created using the provided EntityManager.
     */
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory = JPAQueryFactory(entityManager)
}