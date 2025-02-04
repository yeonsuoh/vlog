package io.vlog.common.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    value = [
        ClientProperties::class,
        ServerProperties::class,
    ]
)
class CommonConfig