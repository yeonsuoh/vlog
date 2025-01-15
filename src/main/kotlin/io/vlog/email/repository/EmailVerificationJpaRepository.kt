package io.vlog.email.repository

import io.vlog.email.domain.entity.EmailVerificationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EmailVerificationJpaRepository : JpaRepository<EmailVerificationEntity, Long> {

    fun findByCode(code: String): EmailVerificationEntity?
}