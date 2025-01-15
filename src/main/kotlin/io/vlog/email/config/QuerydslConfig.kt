package io.vlog.email.config

import com.querydsl.jpa.JPQLTemplates
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuerydslConfig(
    private val entityManager: EntityManager,
) {

    @Bean
    fun jpaQueryFactory() = JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager)
}