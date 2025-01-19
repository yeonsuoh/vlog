package io.vlog.auth.dto

interface OAuth2Response {
    // 제공자 (ex. naver, google, ...)
    fun getProvider(): String

    // 제공자에서 발급해주는 아이디(번호)
    fun getProviderId(): String

    // 이메일
    fun getEmail(): String

    // 사용자 실명
    fun name(): String
}