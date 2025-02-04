package io.vlog.user.repository

import io.vlog.user.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<UserEntity, Long> {
    fun findByUuid(uuid: String): UserEntity?
}