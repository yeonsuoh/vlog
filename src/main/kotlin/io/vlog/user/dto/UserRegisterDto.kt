package io.vlog.user.dto

data class UserRegisterDto (
    val profileName: String,
    val email: String,
    val userId: String,
    val intro: String?,
)