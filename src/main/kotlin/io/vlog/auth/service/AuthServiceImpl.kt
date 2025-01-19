package io.vlog.auth.service

import io.vlog.email.repository.EmailVerificationJpaRepository
import io.vlog.email.repository.EmailVerificationRepository
import io.vlog.email.service.EmailVerificationService
import io.vlog.user.repository.UserJpaRepository
import io.vlog.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AuthServiceImpl(
    private val emailVerificationService: EmailVerificationService,
    private val jwtTokenService: JwtTokenService,
    private val userRepository: UserRepository,
    private val userJpaRepository: UserJpaRepository,
) : AuthService {

    @Transactional
    override fun emailLogin(code: String): String {
        val email = emailVerificationService.getEmailByCode(code)
        val user = userRepository.getByEmail(email)
            ?: throw IllegalArgumentException("user not found")
        val uuid = user.uuid
        user.lastLoginAt = LocalDateTime.now()
        userJpaRepository.save(user)

        // token 생성
        val accessToken = jwtTokenService.createToken(uuid)

        // 로그인에 사용한 코드는 삭제
        emailVerificationService.delete(code)

        // token 반환
        return accessToken
    }
}