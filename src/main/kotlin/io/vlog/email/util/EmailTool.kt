package io.vlog.email.util

import jakarta.mail.MessagingException
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class EmailTool(
    private val javaMailSender: JavaMailSender,
) {
    fun sendEmail(email: String, title: String, text: String) {
        try {
            val message: MimeMessage = javaMailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, true)
            helper.setTo(email)
            helper.setSubject(title)
            helper.setText(text, true) // true는 html 형식을 의미
            javaMailSender.send(message)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
}