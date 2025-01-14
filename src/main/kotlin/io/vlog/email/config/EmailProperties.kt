package io.vlog.email.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
class EmailProperties {
    var host: String? = null
    var port: Int? = 0
    var username: String? = null
    var password: String? = null
    var properties: Properties = Properties()
    var authCodeExpirationMillis: Long = 0

    class Properties {
        var mail: Mail = Mail()

        class Mail {
            var smtp: Smtp = Smtp()

            class Smtp {
                var auth: Boolean = false
                var starttls: Starttls = Starttls()
                var connectionTimeout: Int = 0
                var timeout: Int = 0
                var writeTimeout: Int = 0

                class Starttls {
                    var enable: Boolean = false
                    var required: Boolean = false
                }
            }
        }
    }


    @Bean
    fun mailSender(): JavaMailSender {
        val mailsender = JavaMailSenderImpl()
        mailsender.host = host
        mailsender.port = port!!
        mailsender.username = username
        mailsender.password = password
        mailsender.defaultEncoding = "UTF-8"
        mailsender.javaMailProperties = getMailProperties()

        return mailsender
    }

    private fun getMailProperties(): java.util.Properties {
        val properties = java.util.Properties()
        properties.put("mail.smtp.auth", this.properties.mail.smtp.auth);
        properties.put("mail.smtp.starttls.enable", this.properties.mail.smtp.starttls.enable);
        properties.put("mail.smtp.starttls.required", this.properties.mail.smtp.starttls.required);
        properties.put("mail.smtp.connectiontimeout", this.properties.mail.smtp.connectionTimeout);
        properties.put("mail.smtp.timeout", this.properties.mail.smtp.timeout);
        properties.put("mail.smtp.writetimeout", this.properties.mail.smtp.writeTimeout);

        return properties
    }
}