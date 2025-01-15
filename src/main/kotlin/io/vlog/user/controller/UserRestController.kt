package io.vlog.user.controller

import io.vlog.user.dto.UserRegisterDto
import io.vlog.user.dto.request.UserRegisterRequest
import io.vlog.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserRestController(
    private val userService: UserService,
) {

    @PostMapping("/register")
    fun register(request: UserRegisterRequest): ResponseEntity<Any> {
        val isSuccess = userService.register(
            UserRegisterDto(
                request.profileName,
                request.email,
                request.userId,
                request.intro
            ),
            request.code
        )

        // todo 메인으로 로그인한 상태로 이동
        return ResponseEntity.ok(mapOf(
            "isSuccess" to isSuccess
        ))
    }
}