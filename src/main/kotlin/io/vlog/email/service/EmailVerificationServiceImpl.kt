package io.vlog.email.service

import io.vlog.email.config.EmailProperties
import io.vlog.email.domain.constant.EmailConstant
import io.vlog.email.domain.entity.EmailVerificationEntity
import io.vlog.email.repository.EmailVerificationJpaRepository
import io.vlog.email.repository.EmailVerificationRepository
import io.vlog.email.util.EmailTool
import io.vlog.email.util.EmailUtil
import io.vlog.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class EmailVerificationServiceImpl(
    private val emailTool: EmailTool,
    private val emailVerificationJpaRepository: EmailVerificationJpaRepository,
    private val emailVerificationRepository: EmailVerificationRepository,
    private val emailProperties: EmailProperties,
    private val userRepository: UserRepository,
    private val emailConstant: EmailConstant,
) : EmailVerificationService {
    override fun sendVerificationEmail(email: String): Boolean {
        // 인증 코드 생성
        val code = EmailUtil.generateCode(emailConstant.CODE_LENGTH)

        val user = userRepository.getByEmail(email)

        if (user != null) {
            // 이미 가입된 회원이면 로그인 링크가 포함된 이메일 발송
            sendLoginEmail(email, code)
        } else {
            // 가입된 회원이 아니면 회원가입 링크가 포함된 이메일 발송
            sendRegistrationEmail(email, code)
        }

        // 영속화
        val newEntity = EmailVerificationEntity().apply {
            this.email = email
            this.code = code
            this.expiredAt = LocalDateTime.now().plus(Duration.ofMillis(emailProperties.authCodeExpirationMillis))
        }
        emailVerificationJpaRepository.save(newEntity)
        return true
    }

    override fun getEmailByCode(code: String): String {
        val emailVerificationEntity = emailVerificationJpaRepository.findByCode(code)
            ?: throw IllegalArgumentException("코드가 유효하지 않습니다.")
        if (emailVerificationEntity.deletedAt != null) {
            throw IllegalArgumentException("이미 사용된 코드입니다.")
        }
        if (emailVerificationEntity.expiredAt?.isBefore(LocalDateTime.now()) == true) {
            throw IllegalArgumentException("만료된 코드입니다.")
        }

        return emailVerificationEntity.email
    }

    override fun delete(code: String) {
        val emailVerification = emailVerificationRepository.getByCode(code)
            ?: throw IllegalArgumentException("verification not found")

        emailVerification.deletedAt = LocalDateTime.now()
        emailVerificationJpaRepository.save(emailVerification)
    }

    private fun sendRegistrationEmail(
        email: String,
        code: String
    ) {
        emailTool.sendEmail(
            email,
            emailConstant.REGISTER_EMAIL_TITLE,
            emailConstant.getRegisterEmailContent(code)
        )
    }

    private fun sendLoginEmail(
        email: String,
        code: String,
    ) {
        emailTool.sendEmail(
            email,
            emailConstant.LOGIN_EMAIL_TITLE,
            emailConstant.getLoginEmailContent(code)
        )
    }
}