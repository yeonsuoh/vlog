package io.vlog.auth.service

interface AuthService {
    fun emailLogin(code: String): String
}