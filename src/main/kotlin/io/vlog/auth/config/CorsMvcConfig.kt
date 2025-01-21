package io.vlog.auth.config

import io.vlog.common.domain.constant.WebConstant.CLIENT_SERVER
import io.vlog.common.domain.constant.WebConstant.SET_COOKIE_HEADER
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsMvcConfig() : WebMvcConfigurer {

    @Override
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .exposedHeaders(SET_COOKIE_HEADER)
            .allowedOrigins(CLIENT_SERVER)
    }
}