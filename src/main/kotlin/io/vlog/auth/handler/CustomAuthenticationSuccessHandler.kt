package io.vlog.auth.handler

import io.vlog.auth.domain.CustomOAuth2User
import io.vlog.auth.service.TokenService
import io.vlog.user.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationSuccessHandler(
    private val userRepository: UserRepository,
    private val tokenService: TokenService,
) : AuthenticationSuccessHandler{
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
                val token = tokenService.createToken(it)
                response?.addHeader("Authorization", "Bearer $token")
                response?.sendRedirect("/main")
            }
        } else {
            // 회원가입 페이지로 리다이렉트
            val redirectUrl = "/register/social?email=$email&name=$name&socialType=$socialType&socialId=$socialId"
        }

    }


}