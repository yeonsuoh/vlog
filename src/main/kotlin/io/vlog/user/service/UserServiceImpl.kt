package io.vlog.user.service

import io.vlog.auth.service.JwtTokenService
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
    private val jwtTokenService: JwtTokenService,
    ) : UserService {

    @Transactional
    override fun signup(dto: SignupRequestDto): String {
        // validate
        validateRegister(dto.email, dto.userId)

        val savedUser = userJpaRepository.save(dto.toUserEntity())

        val accessToken = jwtTokenService.createAccessToken(savedUser.uuid)
        // 사용한 code는 삭제
        dto.code?.let { emailVerificationService.delete(it) }

        // token 반환
        return accessToken
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