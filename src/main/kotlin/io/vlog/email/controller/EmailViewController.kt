package io.vlog.email.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class EmailViewController {

    @GetMapping("/register")
    fun registerView(
        @RequestParam code: String,
        model: Model,
    ): String {
        // code로 email 조회
        val email =


        return "register"
    }
}