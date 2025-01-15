package io.vlog.auth.provider

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.vlog.auth.config.JwtProperties
import io.vlog.auth.domain.constant.JwtConstant
import org.springframework.stereotype.Service
import java.util.Date


@Service
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
) {
    fun createToken(uuid: String): String {
        return Jwts.builder()
            .header()
            .add(JwtConstant.TYPE, JwtConstant.JWT)
            .and()
            .claims()
            .add(mapOf(JwtConstant.USER_ID to uuid))
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + jwtProperties.accessExpiration!!))
            .issuer(JwtConstant.ISSUER)
            .subject(JwtConstant.ACCESS_TOKEN)
            .and()
            .signWith(secretKey)
            .compact()
    }

    private val secretKey = Keys.hmacShaKeyFor(
        // 주어진 바이트 배열을 기반으로 HMAC-SHA 알고리즘에 사용될 비밀키를 생성. 비밀키는 서명 생성과 검증에 사용됨.
        jwtProperties.secret!!.toByteArray(), // jwtProperties의 key 프로퍼티 값을 바이트 배열로 변환
    )
}