package io.vlog.auth.controller

import io.vlog.auth.service.AuthService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AuthController(
    private val authService: AuthService,
) {

    @GetMapping("/email-login")
    @ResponseBody
    fun emailLogin(
        @RequestParam code: String,
    ) : String {
        // todo userId uuid 넣기
        val accessToken = authService.emailLogin(code)

        return accessToken

    }
}