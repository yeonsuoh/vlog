package io.vlog.auth.handler

import io.vlog.auth.domain.CustomOAuth2User
import io.vlog.auth.domain.constant.JwtConstant.ACCESS_TOKEN_HEADER
import io.vlog.common.domain.constant.WebConstant
import io.vlog.auth.service.TokenService
import io.vlog.common.config.ClientProperties
import io.vlog.user.repository.UserRepository
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationSuccessHandler(
    private val userRepository: UserRepository,
    private val tokenService: TokenService,
    private val clientProperties: ClientProperties,
    ) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication
    ) {
        val customOAuth2User = authentication.principal as CustomOAuth2User
        val email = customOAuth2User.getEmail()
        val name = customOAuth2User.name
        val socialType = customOAuth2User.getSocialType()
        val socialId = customOAuth2User.getSocialId()

        if (userRepository.existsByEmail(email)) {
            val uuid = customOAuth2User.getUuid()?.let {
                val token = tokenService.createAccessToken(it)
//                response?.addHeader("Authorization", "Bearer $token")
                response?.addCookie(createCookie(ACCESS_TOKEN_HEADER, token))
                response?.sendRedirect(clientProperties.url) // 클라이언트 메인 페이지
            }
        } else {
            // signup token에 사용자 정보를 담아 전달
            val signupToken = tokenService.createSignupToken(email, name, socialType.toString(), socialId)
            val redirectUrl = "${clientProperties.url}/register/social?token=$signupToken"
            response?.sendRedirect(redirectUrl)
        }

    }

    private fun createCookie(key: String, value: String): Cookie {
        val cookie = Cookie(key, value)
        cookie.maxAge = 60 * 60 * 60 // 60 시간
        cookie.path = "/" // 모든 경로
        cookie.isHttpOnly = true // http 요청을 통해서만 전송

        return cookie
    }


}