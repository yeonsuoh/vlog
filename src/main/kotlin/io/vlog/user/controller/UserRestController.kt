package io.vlog.user.controller

import io.vlog.common.config.ClientProperties
import io.vlog.email.service.EmailVerificationService
import io.vlog.user.dto.request.SignupRequest
import io.vlog.user.service.UserService
import io.vlog.user.util.toDto
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/v1/api/user")
class UserRestController(
    private val userService: UserService,
    private val emailVerificationService: EmailVerificationService,
) {

    @PostMapping("/signup")
    fun signup(
        @RequestBody request: SignupRequest,
    ): ResponseEntity<Any> {
        val accessToken = userService.signup(request.toDto())

        return ResponseEntity.ok(mapOf("accessToken" to accessToken))
    }
    @GetMapping("/signup/verify-email")
    fun verifyEmailByCode(
        @RequestParam code: String
    ): ResponseEntity<Any> {
        val emailByCode = emailVerificationService.getEmailByCode(code)

        return ResponseEntity.ok(
            mapOf("email" to emailByCode)
        )
    }
}