package io.vlog.auth.filter

import io.vlog.auth.domain.constant.JwtConstant
import io.vlog.auth.service.JwtTokenService
import io.vlog.auth.service.TokenService
import io.vlog.user.repository.UserJpaRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val tokenService: TokenService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader(JwtConstant.ACCESS_TOKEN_HEADER)

        if (authHeader.doesNotContainBearerToken()) {
            filterChain.doFilter(request, response)
            return
        }
        val jwtToken = authHeader!!.extractTokenValue()

        if (tokenService.isValid(jwtToken)) {
            val authToken = UsernamePasswordAuthenticationToken(tokenService.extractUserId(jwtToken), null, null)
            SecurityContextHolder.getContext().authentication = authToken
        }
        filterChain.doFilter(request, response)
    }

    private fun String?.doesNotContainBearerToken(): Boolean {
        return (this == null || !this.startsWith(JwtConstant.ACCESS_TOKEN_PREFIX))
    }

    private fun String.extractTokenValue(): String {
        return this.substringAfter(JwtConstant.ACCESS_TOKEN_PREFIX)
    }

}