package io.vlog.auth.controller

import io.vlog.auth.annotation.docs.EmailLoginApiDocs
import io.vlog.auth.service.AuthService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
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
    ): RedirectView {
        val accessToken = authService.emailLogin(code)

        // accessToken을 쿠키에 저장
        val cookie = Cookie("Authorization", accessToken).apply {
            path = "/"
            isHttpOnly = false // JS에서 접근 가능 (필요시 Local Storage로 복사)
//            secure = true // HTTPS에서만 전송 (로컬 테스트용 주석 처리)
            maxAge = 60 * 60 * 24 // 1일
        }
        response.addCookie(cookie)

        return RedirectView("/")
    }
}