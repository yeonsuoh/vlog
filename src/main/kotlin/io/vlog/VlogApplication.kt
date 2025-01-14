package io.vlog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VlogApplication

fun main(args: Array<String>) {
	runApplication<VlogApplication>(*args)
}
