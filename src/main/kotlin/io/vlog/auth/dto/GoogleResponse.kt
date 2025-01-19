package io.vlog.auth.dto

import io.vlog.auth.domain.constant.OAuth2Constant
import io.vlog.user.domain.enum.SocialType

data class GoogleResponse(
    val attribute: Map<String, Any>
) : OAuth2Response{

    override fun getProvider(): String {
        return SocialType.GOOGLE.title
    }

    override fun getProviderId(): String {
        return attribute[OAuth2Constant.SUB].toString()
    }

    override fun getEmail(): String {
        return attribute[OAuth2Constant.EMAIL].toString()
    }

    override fun name(): String {
        return attribute[OAuth2Constant.NAME].toString()
    }
}