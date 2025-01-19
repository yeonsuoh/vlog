package io.vlog.user.util

import io.vlog.user.dto.UserRegisterDto
import io.vlog.user.dto.request.UserRegisterRequest

fun UserRegisterRequest.toDto(): UserRegisterDto {
    return UserRegisterDto(
        profileName = this.profileName,
        email = this.email,
        userId = this.userId,
        intro = this.intro
    )
}