package io.vlog.user.service

import io.vlog.email.service.EmailVerificationService
import io.vlog.user.dto.SignupRequestDto
import io.vlog.user.repository.UserJpaRepository
import io.vlog.user.repository.UserRepository
import io.vlog.user.util.toUserEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userJpaRepository: UserJpaRepository,
    private val userRepository: UserRepository,
    private val emailVerificationService: EmailVerificationService,
) : UserService {

    @Transactional
    override fun signup(dto: SignupRequestDto): Boolean {
        // validate
        validateRegister(dto.email, dto.userId)

        userJpaRepository.save(dto.toUserEntity())

        // 사용한 code는 삭제
        dto.code?.let { emailVerificationService.delete(it) }

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
}