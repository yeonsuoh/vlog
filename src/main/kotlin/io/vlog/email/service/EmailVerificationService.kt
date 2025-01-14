package io.vlog.email.service

interface EmailVerificationService {
    fun sendVerificationEmail(email: String): Boolean

    fun getEmail(code: String): String

}