package io.vlog.auth.service

import io.vlog.auth.provider.JwtTokenProvider
import io.vlog.email.service.EmailVerificationService
import io.vlog.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val emailVerificationService: EmailVerificationService,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository,
): AuthService {
    override fun emailLogin(code: String): String {
        val email = emailVerificationService.getEmailByCode(code)
        val uuid = userRepository.getByEmail(email)?.id
            ?: throw IllegalArgumentException("user not found")

        // token 생성 및 반환
        return jwtTokenProvider.createToken(uuid.toString())
    }
}