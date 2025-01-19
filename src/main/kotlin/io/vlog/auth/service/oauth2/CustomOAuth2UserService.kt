package io.vlog.auth.service.oauth2

import io.vlog.auth.domain.CustomOAuth2User
import io.vlog.auth.dto.GoogleResponse
import io.vlog.auth.dto.NaverResponse
import io.vlog.auth.dto.OAuth2Response
import io.vlog.user.domain.enum.SocialType
import io.vlog.user.repository.UserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository,
) : DefaultOAuth2UserService() {

    @Override
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User? {
        val oAuth2User: OAuth2User = super.loadUser(userRequest)
        val attributes = oAuth2User.attributes
        val registrationId = userRequest.clientRegistration.registrationId

        val response = when (registrationId) {
            SocialType.GOOGLE.title -> GoogleResponse(attributes)
            SocialType.NAVER.title -> NaverResponse(attributes)
            else -> throw IllegalArgumentException("unsupported provider : $registrationId")
        }

        // 회원 여부 확인
        val email = response.getEmail()
        val user = userRepository.getByEmail(email)

        return CustomOAuth2User(user, response, attributes)
    }
}