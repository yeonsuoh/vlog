package io.vlog.common.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "vlog.client")
data class ClientProperties(
    val url: String,
)