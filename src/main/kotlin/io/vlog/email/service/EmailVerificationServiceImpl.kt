package io.vlog.email.service

import io.vlog.email.config.EmailProperties
import io.vlog.email.domain.constant.EmailConstant
import io.vlog.email.domain.entity.EmailVerificationEntity
import io.vlog.email.repository.EmailVerificationRepository
import io.vlog.email.util.EmailTool
import io.vlog.email.util.EmailUtil
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class EmailVerificationServiceImpl(
    private val emailTool: EmailTool,
    private val emailVerificationRepository: EmailVerificationRepository,
    private val emailProperties: EmailProperties,
) : EmailVerificationService {
    override fun sendVerificationEmail(email: String): Boolean {
        // todo 이미 가입된 회원이면 로그인 링크 발송

        // 인증 코드 생성
        val code = EmailUtil.generateCode(EmailConstant.CODE_LENGTH)

        // 영속화
        val newEntity = EmailVerificationEntity().apply {
            this.email = email
            this.code = code
            this.expiredAt = LocalDateTime.now().plus(Duration.ofMillis(emailProperties.authCodeExpirationMillis))
        }
        emailVerificationRepository.save(newEntity)

        // 회원 가입 링크가 포함된 이메일 전송
        sendRegistrationEmail(email, code)

        return true
    }

    override fun getEmail(code: String): String {
        val email = emailVerificationRepository.findByCode(code)?.email
            ?: throw IllegalArgumentException("email not found")

        return email
    }

    private fun sendRegistrationEmail(
        email: String,
        code: String
    ) {
        emailTool.sendEmail(
            email,
            EmailConstant.REGISTER_EMAIL_TITLE,
            EmailConstant.getRegisterEmailContent(code)
        )
    }
}