package io.vlog.auth.config

import io.vlog.auth.domain.constant.JwtConstant.ACCESS_TOKEN_HEADER
import io.vlog.common.config.ClientProperties
import io.vlog.common.domain.constant.WebConstant.SET_COOKIE_HEADER
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsMvcConfig(
    private val clientProperties: ClientProperties,
) : WebMvcConfigurer {



    @Override
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .exposedHeaders(SET_COOKIE_HEADER, ACCESS_TOKEN_HEADER)
            .allowedOrigins(clientProperties.url)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}