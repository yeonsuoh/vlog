package io.vlog.email.repository

import io.vlog.email.domain.entity.EmailVerificationEntity

interface EmailVerificationRepository {

    fun getByCode(code: String): EmailVerificationEntity?
}