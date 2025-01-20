package io.vlog.user.dto

import io.vlog.user.domain.enum.SocialType

data class SignupRequestDto (
    val profileName: String,
    val email: String,
    val userId: String,
    val intro: String?,
    val code: String?,
    val socialType : SocialType?,
    val socialId: String?
)