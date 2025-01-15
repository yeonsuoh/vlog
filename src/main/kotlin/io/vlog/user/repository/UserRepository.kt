package io.vlog.user.repository

import io.vlog.user.domain.UserEntity

interface UserRepository {
    fun existsByEmail(email: String): Boolean

    fun existsByUserId(userId: String): Boolean

    fun getByEmail(email: String): UserEntity?
}