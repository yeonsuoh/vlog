package io.vlog.common.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "vlog.server")
data class ServerProperties(
    val url: String,
)