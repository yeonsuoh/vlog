package io.vlog.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "vlog.jwt")
class JwtProperties {
    var secret: String? = null
    var accessExpiration: Long? = 0
    var refreshExpiration: Long? = 0
}