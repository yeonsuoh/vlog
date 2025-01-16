package io.vlog.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "vlog.auth")
data class AuthPermitProperties (
    val permitAllUrls: Array<String>,
)