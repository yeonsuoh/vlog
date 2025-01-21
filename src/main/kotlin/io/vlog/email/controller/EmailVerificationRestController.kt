package io.vlog.email.controller

import io.vlog.email.service.EmailVerificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/email")
class EmailVerificationRestController(
    private val emailVerificationService: EmailVerificationService,
) {
    @PostMapping("/verification")
    fun sendEmail(
        @RequestParam("email")
        email: String,
    ) : ResponseEntity<Any> {
        val isSuccess = emailVerificationService.sendVerificationEmail(email)

        return ResponseEntity.ok(
            mapOf("isSuccess" to isSuccess)
        )
    }
}