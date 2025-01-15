package io.vlog.user.service

import io.vlog.user.dto.UserRegisterDto

interface UserService {
    fun register(
        dto: UserRegisterDto,
        code: String,
        ) : Boolean
}