package io.vlog.main.controller

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.server.ResponseStatusException

@Controller
class MainController {

    @ResponseBody
    @GetMapping("/main")
    fun main() : String {
        return "to do "
    }

    @ResponseBody
    @GetMapping("/test")
    fun test() : String {
        return "return data for test"
    }
}