package io.vlog.auth.config

import io.vlog.auth.domain.constant.JwtConstant.ACCESS_TOKEN_HEADER
import io.vlog.auth.domain.constant.WebConstant.CLIENT_SERVER
import io.vlog.auth.domain.constant.WebConstant.SET_COOKIE_HEADER
import io.vlog.auth.filter.JwtAuthenticationFilter
import io.vlog.auth.handler.CustomAuthenticationSuccessHandler
import io.vlog.auth.service.oauth2.CustomOAuth2UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import java.util.*

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(
    value = [AuthPermitProperties::class]
)
class SecurityConfig(
    private val authPermitProperties: AuthPermitProperties,
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
) {
    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter,
    ): DefaultSecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .oauth2Login { oauth2 ->
                oauth2.userInfoEndpoint {
                    it.userService(customOAuth2UserService)
                }
                oauth2.successHandler(customAuthenticationSuccessHandler)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers(*authPermitProperties.permitAllUrls).permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .cors {
                it.configurationSource {
                    val configuration = CorsConfiguration()
                    configuration.allowedOrigins = Collections.singletonList(CLIENT_SERVER) // 허용할 Origin
                    configuration.allowedMethods = Collections.singletonList("*") // 허용할 HTTP 메서드
                    configuration.allowedHeaders = Collections.singletonList("*")
                    configuration.allowCredentials = true // 쿠키 허용 여부
                    configuration.maxAge = 3600L

                    configuration.exposedHeaders = Collections.singletonList(SET_COOKIE_HEADER)
                    configuration.exposedHeaders = Collections.singletonList(ACCESS_TOKEN_HEADER)

                    configuration
                }
            }

            .build()
    }


}