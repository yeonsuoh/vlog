package io.vlog.user.service

import io.vlog.email.repository.EmailVerificationJpaRepository
import io.vlog.email.repository.EmailVerificationRepository
import io.vlog.user.domain.UserEntity
import io.vlog.user.dto.UserRegisterDto
import io.vlog.user.repository.UserJpaRepository
import io.vlog.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserServiceImpl(
    private val userJpaRepository: UserJpaRepository,
    private val userRepository: UserRepository,
    private val emailVerificationJpaRepository: EmailVerificationJpaRepository,
    private val emailVerificationRepository: EmailVerificationRepository,
) : UserService {

    @Transactional
    override fun register(
        dto: UserRegisterDto,
        code: String,
    ): Boolean {
        // validate
        if (userRepository.existsByEmail(dto.email)) {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다.")
        }

        if (userRepository.existsByUserId(dto.userId)) {
            throw IllegalArgumentException("이미 존재하는 아이디입니다.")
        }

        // 영속화
        val newEntity = UserEntity().apply {
            this.profileName = dto.profileName
            this.email = dto.email
            this.userId = dto.userId
            this.intro = dto.intro
        }

        userJpaRepository.save(newEntity)

        // 사용한 code는 삭제
        val emailVerification = emailVerificationRepository.getByCode(code)

        emailVerification?.let {
            it.deletedAt = LocalDateTime.now()
            emailVerificationJpaRepository.save(emailVerification)
        }

        return true
    }
}