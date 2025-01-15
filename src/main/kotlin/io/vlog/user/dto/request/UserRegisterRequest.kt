package io.vlog.user.dto.request

import org.jetbrains.annotations.NotNull

data class UserRegisterRequest (
    @field:NotNull
    val profileName: String,
    @field:NotNull
    val email: String,
    @field:NotNull
    val userId: String,
    val intro: String?,
    @field:NotNull
    val code: String,
) {
    init {
        require(userId.matches(Regex("^[a-z0-9_-]{3,16}$"))) {
            "사용자 ID는 3~16자의 알파벳 소문자, 숫자, '-', '_'만 허용됩니다."
        }
    }
}