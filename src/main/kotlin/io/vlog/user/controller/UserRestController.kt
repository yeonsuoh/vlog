package io.vlog.user.controller

import io.vlog.user.domain.enum.SocialType
import io.vlog.user.dto.UserRegisterDto
import io.vlog.user.dto.request.UserRegisterRequest
import io.vlog.user.service.UserService
import io.vlog.user.util.toDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/api/user")
class UserRestController(
    private val userService: UserService,
) {

    /**
     * 자체 회원가입 API
     */
    @PostMapping("/register")
    fun register(request: UserRegisterRequest): ResponseEntity<Any> {
        val isSuccess = userService.register(request.toDto(), request.code!!)

        // todo 메인으로 로그인한 상태로 이동
        return ResponseEntity.ok(
            mapOf(
                "isSuccess" to isSuccess
            )
        )
    }

    /**
     * 소셜 로그인으로 회원가입 진행 API
     */
    @PostMapping("/register/social")
    fun socialRegister(request: UserRegisterRequest): ResponseEntity<Any> {
        val isSuccess = userService.register(
            request.toDto(),
            SocialType.fromTitle(request.socialType!!)!!,
            request.socialId!!
        )

        // todo 메인으로 로그인한 상태로 이동
        return ResponseEntity.ok(
            mapOf(
                "isSuccess" to isSuccess
            )
        )
    }
}