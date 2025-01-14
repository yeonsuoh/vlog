package io.vlog.email.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "email_verification")
class EmailVerificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    var email: String? = null


    var code: String? = null

    var expiredAt: LocalDateTime? = null

    @CreatedDate
    var createdAt: LocalDateTime? = LocalDateTime.now()

    @LastModifiedDate
    var updatedAt: LocalDateTime? = LocalDateTime.now()

    var deletedAt: LocalDateTime? = null

    var checkCnt: Byte? = 0

}