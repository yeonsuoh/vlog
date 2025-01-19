package io.vlog.user.controller

import io.vlog.email.service.EmailVerificationService
import io.vlog.user.domain.constant.UserConstant
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserViewController(
    private val emailVerificationService: EmailVerificationService,
    ) {
    /**
     * 자체 회원가입 페이지 뷰
     */
    @GetMapping("/register")
    fun registerView(
        @RequestParam code: String,
        model: Model,
    ): String {
        // code로 email 조회
        val email = emailVerificationService.getEmailByCode(code)

        model.addAttribute(UserConstant.EMAIL, email)
        model.addAttribute(UserConstant.CODE, code)

        return "register"
    }

    /**
     * 소셜 로그인 시 회원가입 페이지 뷰
     */
    @GetMapping("/register/social")
    fun socialRegisterView(
        @RequestParam email: String,
        @RequestParam name: String,
        @RequestParam socialType: String,
        @RequestParam socialId: String,
        model: Model,
    ) : String {
        model.addAttribute(UserConstant.EMAIL, email)
        model.addAttribute(UserConstant.NAME, name)
        model.addAttribute(UserConstant.SOCIAL_TYPE, socialType)
        model.addAttribute(UserConstant.SOCIAL_ID, socialId)

        return "register"
    }

}