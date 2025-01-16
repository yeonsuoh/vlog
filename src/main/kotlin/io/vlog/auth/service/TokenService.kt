package io.vlog.auth.service

interface TokenService {
    fun createToken(uuid: String): String

    fun isValid(token: String): Boolean

    fun extractUserId(token: String): Long
}