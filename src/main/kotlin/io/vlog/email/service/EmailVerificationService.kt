package io.vlog.email.service

interface EmailVerificationService {
    fun sendVerificationEmail(email: String): Boolean

    fun getEmailByCode(code: String): String

}