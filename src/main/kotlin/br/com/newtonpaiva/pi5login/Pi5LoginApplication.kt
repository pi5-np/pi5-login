package br.com.newtonpaiva.pi5login

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class Pi5LoginApplication

fun main(args: Array<String>) {
	runApplication<Pi5LoginApplication>(*args)

	println("Key:" + BCryptPasswordEncoder().encode("10686199"))

}
