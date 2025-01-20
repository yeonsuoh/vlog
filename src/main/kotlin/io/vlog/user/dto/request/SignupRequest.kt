package io.vlog.user.dto.request

import io.vlog.common.enum.YnType
import io.vlog.user.domain.enum.SocialType
import org.jetbrains.annotations.NotNull

data class SignupRequest(
    @field:NotNull
    val profileName: String,
    @field:NotNull
    val email: String,
    @field:NotNull
    val userId: String,
    val intro: String? = null,
    val code: String? = null, // 자체 회원가입
    val socialType: SocialType? = null, // oAuth2 provider
    val socialId: String? = null, // oAuth2 id
) {
    init {
        require(userId.matches(Regex("^[a-z0-9_-]{3,16}$"))) {
            "사용자 ID는 3~16자의 알파벳 소문자, 숫자, '-', '_'만 허용됩니다."
        }
    }
}