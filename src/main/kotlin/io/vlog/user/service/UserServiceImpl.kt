package io.vlog.user.service

import io.vlog.email.repository.EmailVerificationJpaRepository
import io.vlog.email.repository.EmailVerificationRepository
import io.vlog.email.service.EmailVerificationService
import io.vlog.user.domain.UserEntity
import io.vlog.user.domain.enum.SocialType
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
    private val emailVerificationService: EmailVerificationService,
) : UserService {

    @Transactional
    override fun register(
        dto: UserRegisterDto,
        code: String,
    ): Boolean {
        // validate
        validateRegister(dto.email, dto.userId)

        // 영속화
        saveUser(dto)

        // 사용한 code는 삭제
        emailVerificationService.delete(code)

        return true
    }

    @Transactional
    override fun register(
        dto: UserRegisterDto,
        socialType: SocialType,
        socialId: String
    ): Boolean {
        // validate
        validateRegister(dto.email, dto.userId)

        // 영속화
        saveUser(dto, socialType, socialId)

        return true
    }

    private fun validateRegister(
        email: String,
        userId: String,
    ) {
        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다.")
        }

        if (userRepository.existsByUserId(userId)) {
            throw IllegalArgumentException("이미 존재하는 아이디입니다.")
        }
    }

    private fun saveUser(
        dto: UserRegisterDto,
    ) {
        val newEntity = UserEntity().apply {
            this.profileName = dto.profileName
            this.email = dto.email
            this.userId = dto.userId
            this.intro = dto.intro
        }
        userJpaRepository.save(newEntity)
    }

    private fun saveUser(
        dto: UserRegisterDto,
        socialType: SocialType,
        socialId: String
    ) {
        val newEntity = UserEntity().apply {
            this.profileName = dto.profileName
            this.email = dto.email
            this.userId = dto.userId
            this.intro = dto.intro
            this.socialType = socialType
            this.socialId = socialId
        }
        userJpaRepository.save(newEntity)
    }
}