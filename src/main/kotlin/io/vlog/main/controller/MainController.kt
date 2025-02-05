package io.vlog.main.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.server.ResponseStatusException

@Controller
class MainController {

    @ResponseBody
    @GetMapping("/main") // 인증 없이 접근 가능
    fun main() : String {
        return "to do "
    }

    @ResponseBody
    @GetMapping("/test") // 인증 필요
    fun test() : ResponseEntity<Any> {
        return ResponseEntity.ok(
            mapOf("result" to "토큰 필요 요청 성공")
        )
    }
}