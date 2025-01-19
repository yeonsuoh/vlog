package io.vlog.auth.dto

import io.vlog.auth.domain.constant.OAuth2Constant
import io.vlog.user.domain.enum.SocialType

data class NaverResponse(
    val attribute: Map<String, Any>
) : OAuth2Response {

    private val attributes: Map<String, Any> = attribute[OAuth2Constant.RESPONSE] as Map<String, Any>
    override fun getProvider(): String {
        return SocialType.NAVER.title
    }

    override fun getProviderId(): String {
        return attributes[OAuth2Constant.ID].toString()
    }

    override fun getEmail(): String {
        return attributes[OAuth2Constant.EMAIL].toString()
    }

    override fun name(): String {
        return attributes[OAuth2Constant.NAME].toString()
    }

}