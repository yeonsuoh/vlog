package io.vlog.user.domain.enum

enum class SocialType(val title: String) {
    GOOGLE("google"),
    NAVER("naver");

    companion object {
        fun fromTitle(title: String): SocialType? {
            return entries.find { it.title.equals(title, ignoreCase = true) }
        }
    }
}