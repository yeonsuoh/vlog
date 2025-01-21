package io.vlog.auth.service

interface TokenService {
    fun createAccessToken(uuid: String): String

    fun createSignupToken(
        email: String,
        name: String,
        socialType: String,
        socialId: String,
    ) : String

    fun isValid(token: String): Boolean

    fun extractUserId(token: String): Long
}