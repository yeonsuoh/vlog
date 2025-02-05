package io.vlog.auth.controller

import io.vlog.auth.annotation.docs.EmailLoginApiDocs
import io.vlog.auth.service.AuthService
import io.vlog.common.config.ClientProperties
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
class AuthRestController(
    private val authService: AuthService,
) {
    @GetMapping("/email-login")
    @EmailLoginApiDocs
    fun emailLogin(
        @RequestParam code: String,
        response: HttpServletResponse,
    ): ResponseEntity<Any> {
        val accessToken = authService.emailLogin(code)

        return ResponseEntity.ok(mapOf("accessToken" to accessToken))
    }
}