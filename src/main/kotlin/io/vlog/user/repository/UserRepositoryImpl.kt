package io.vlog.user.repository

import io.vlog.common.repository.BaseRepository
import io.vlog.user.domain.QUserEntity
import io.vlog.user.domain.QUserEntity.userEntity
import io.vlog.user.domain.UserEntity
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl() : UserRepository, BaseRepository() {
    override fun existsByEmail(email: String): Boolean {
        return queryFactory
            .selectOne()
            .from(userEntity)
            .where(
                userEntity.email.eq(email)
                    .and(userEntity.deletedAt.isNull)
            )
            .fetchFirst() != null
    }

    override fun existsByUserId(userId: String): Boolean {
        return queryFactory
            .selectOne()
            .from(userEntity)
            .where(
                userEntity.userId.eq(userId)
                    .and(userEntity.deletedAt.isNull)
            )
            .fetchFirst() != null
    }

    override fun getByEmail(email: String): UserEntity? {
        return queryFactory
            .select(userEntity)
            .from(userEntity)
            .where(
                userEntity.email.eq(email)
                    .and(userEntity.deletedAt.isNull)
            )
            .fetchOne()
    }
}