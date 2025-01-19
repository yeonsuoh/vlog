package io.vlog.user.service

import io.vlog.user.domain.enum.SocialType
import io.vlog.user.dto.UserRegisterDto

interface UserService {
    fun register(
        dto: UserRegisterDto,
        code: String,
    ): Boolean

    fun register(
        dto: UserRegisterDto,
        socialType: SocialType,
        socialId: String,
    ): Boolean
}