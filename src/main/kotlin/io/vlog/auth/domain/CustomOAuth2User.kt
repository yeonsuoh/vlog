package io.vlog.auth.domain

import io.jsonwebtoken.lang.Collections
import io.vlog.auth.dto.OAuth2Response
import io.vlog.user.domain.entity.UserEntity
import io.vlog.user.domain.enum.SocialType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2User(
    private val user: UserEntity?,
    private val response: OAuth2Response,
    private val attributes: Map<String, Any>
) : OAuth2User {
    override fun getName(): String {
        return user?.profileName ?: response.name()
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return attributes.toMutableMap()
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        // todo 권한관리
        return Collections.emptyList()
    }

    fun getEmail(): String {
        return user?.email ?: response.getEmail()
    }

    fun getSocialType(): SocialType? {
        return user?.socialType ?: SocialType.fromTitle(response.getProvider())
    }

    fun getSocialId(): String {
        return user?.socialId ?: response.getProviderId()
    }

    fun getUuid(): String? {
        return user?.uuid
    }
}