package io.vlog.auth.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.vlog.auth.config.JwtProperties
import io.vlog.auth.domain.constant.JwtConstant
import io.vlog.email.config.EmailProperties
import io.vlog.user.repository.UserJpaRepository
import org.springframework.stereotype.Service
import java.util.Date


@Service
class JwtTokenService(
    private val jwtProperties: JwtProperties,
    private val emailProperties: EmailProperties,
    private val userJpaRepository: UserJpaRepository,
) : TokenService {
    override fun createAccessToken(uuid: String): String {
        return Jwts.builder()
            .header()
            .add(JwtConstant.TYPE, JwtConstant.JWT)
            .and()
            .claims()
            .add(mapOf(JwtConstant.USER_ID to uuid))
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + emailProperties.authCodeExpirationMillis))
            .issuer(JwtConstant.ISSUER)
            .subject(JwtConstant.SIGNUP_TOKEN)
            .and()
            .signWith(secretKey)
            .compact()
    }

    override fun createSignupToken(email: String, name: String, socialType: String, socialId: String): String {
        return Jwts.builder()
            .header()
            .add(JwtConstant.TYPE, JwtConstant.JWT)
            .and()
            .claims()
            .add(mapOf(JwtConstant.EMAIL to email))
            .add(mapOf(JwtConstant.NAME to name))
            .add(mapOf(JwtConstant.SOCIAL_TYPE to socialType))
            .add(mapOf(JwtConstant.SOCIAL_ID to socialId))
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + jwtProperties.accessExpiration!!))
            .issuer(JwtConstant.ISSUER)
            .subject(JwtConstant.ACCESS_TOKEN)
            .and()
            .signWith(secretKey)
            .compact()
    }

    override fun isValid(token: String): Boolean {
        return getAllClaims(token)
            .expiration.after(Date())
    }

    override fun extractUserId(token: String): Long {
        val uuid = getAllClaims(token).get(JwtConstant.USER_ID).toString()
        val user = userJpaRepository.findByUuid(uuid)
            ?: throw IllegalArgumentException("user not found")
        return user.id
    }

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser
            .parseSignedClaims(token)
            .payload
    }

    private val secretKey = Keys.hmacShaKeyFor(
        // 주어진 바이트 배열을 기반으로 HMAC-SHA 알고리즘에 사용될 비밀키를 생성. 비밀키는 서명 생성과 검증에 사용됨.
        jwtProperties.secret!!.toByteArray(), // jwtProperties의 key 프로퍼티 값을 바이트 배열로 변환
    )
}