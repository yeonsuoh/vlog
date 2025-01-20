package io.vlog.user.controller

import io.vlog.user.dto.request.SignupRequest
import io.vlog.user.service.UserService
import io.vlog.user.util.toDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/user")
class UserRestController(
    private val userService: UserService,
) {

    @PostMapping("/signup")
    fun signup(request: SignupRequest) : ResponseEntity<Any> {
        val isSuccess = userService.signup(request.toDto())

        return ResponseEntity.ok(
            mapOf("isSuccess" to isSuccess)
        )
    }
}