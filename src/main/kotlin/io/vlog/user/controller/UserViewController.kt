package io.vlog.user.controller

import io.vlog.email.service.EmailVerificationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserViewController(
    private val emailVerificationService: EmailVerificationService,
    ) {
    @GetMapping("/register")
    fun registerView(
        @RequestParam code: String,
        model: Model,
    ): String {
        // code로 email 조회
        val email = emailVerificationService.getEmailByCode(code)

        model.addAttribute("email", email)
        model.addAttribute("code", code)

        return "register"
    }

}