package io.vlog.email.util

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class EmailTool(
    private val javaMailSender: JavaMailSender,
) {
    fun sendEmail(email: String, title: String, text: String) {
        val emailForm = createEmailForm(email, title, text)
        javaMailSender.send(emailForm)
    }

    private fun createEmailForm(
        toEmail: String,
        title: String,
        text: String,
    ): SimpleMailMessage {
        val message = SimpleMailMessage()
        message.setTo(toEmail)
        message.subject = title
        message.text = text

        return message
    }
}