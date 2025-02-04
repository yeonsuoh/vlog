package io.vlog.user.util

import io.vlog.user.domain.entity.UserEntity
import io.vlog.user.domain.enum.SocialType
import io.vlog.user.dto.SignupRequestDto
import io.vlog.user.dto.request.SignupRequest

fun SignupRequest.toDto(): SignupRequestDto {
    return SignupRequestDto(
        profileName, email, userId, intro, code, socialType?.let {
            kotlin.runCatching {
                SocialType.valueOf(it)
            }.getOrNull()
        }, socialId
    )
}

fun SignupRequestDto.toUserEntity(): UserEntity {
    return UserEntity().apply {
        this.profileName = this@toUserEntity.profileName
        this.email = this@toUserEntity.email
        this.userId = this@toUserEntity.userId
        this.intro = this@toUserEntity.intro
        this.socialType = this@toUserEntity.socialType
        this.socialId = this@toUserEntity.socialId
    }
}