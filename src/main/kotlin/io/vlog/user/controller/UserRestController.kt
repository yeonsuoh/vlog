package io.vlog.user.controller

import io.vlog.email.service.EmailVerificationService
import io.vlog.user.dto.request.SignupRequest
import io.vlog.user.service.UserService
import io.vlog.user.util.toDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/user")
class UserRestController(
    private val userService: UserService,
    private val emailVerificationService: EmailVerificationService,
) {

    @PostMapping("/signup")
    fun signup(
        @RequestBody request: SignupRequest
    ): ResponseEntity<Any> {
        val isSuccess = userService.signup(request.toDto())

        // todo token 반환!!

        return ResponseEntity.ok(
            mapOf("isSuccess" to isSuccess)
        )
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