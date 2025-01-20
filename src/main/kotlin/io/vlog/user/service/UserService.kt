package io.vlog.user.service

import io.vlog.user.domain.enum.SocialType
import io.vlog.user.dto.SignupRequestDto

interface UserService {
    fun signup(
        dto: SignupRequestDto
    ) : Boolean
}