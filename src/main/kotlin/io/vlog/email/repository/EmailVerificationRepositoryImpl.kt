package io.vlog.email.repository

import io.vlog.common.repository.BaseRepository
import io.vlog.email.domain.entity.EmailVerificationEntity
import io.vlog.email.domain.entity.QEmailVerificationEntity.*
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class EmailVerificationRepositoryImpl() : EmailVerificationRepository, BaseRepository() {
    override fun getByCode(code: String): EmailVerificationEntity? {
        val now = LocalDateTime.now()

        return queryFactory
            .select(emailVerificationEntity)
            .from(emailVerificationEntity)
            .where(
                emailVerificationEntity.code.eq(code)
                    .and(emailVerificationEntity.deletedAt.isNull)
                    .and(emailVerificationEntity.expiredAt.gt(now))
            )
            .fetchFirst()
    }
}