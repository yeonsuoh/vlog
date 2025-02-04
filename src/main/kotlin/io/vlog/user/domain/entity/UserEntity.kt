package io.vlog.user.domain.entity

import io.vlog.user.domain.enum.SocialType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UuidGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name= "users", uniqueConstraints = [
    UniqueConstraint(columnNames = ["email"]),
    UniqueConstraint(columnNames = ["userId"])
])
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long = 0

    @Column(nullable = false, unique = true)
    var uuid: String  = UUID.randomUUID().toString()

    @Column(nullable = false)
    var profileName: String =""

    @Column(nullable = false, unique = true)
    var email: String = ""

    @Column(nullable = false, unique = true)
    var userId: String = ""

    var intro: String? = null

    var lastLoginAt: LocalDateTime? = null

    var socialType: SocialType? = null

    var socialId: String? = null

    @CreatedDate
    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()

    var deletedAt: LocalDateTime? = null
}