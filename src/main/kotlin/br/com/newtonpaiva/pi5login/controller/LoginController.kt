package br.com.newtonpaiva.pi5login.controller

import br.com.newtonpaiva.pi5login.entity.dto.LoginRequest
import br.com.newtonpaiva.pi5login.entity.dto.LoginResponse
import br.com.newtonpaiva.pi5login.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class LoginController {

    @Autowired
    lateinit var loginService: LoginService

    @PostMapping
    fun login(@RequestBody loginRequest: LoginRequest) : ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(loginService.login(loginRequest))
        } catch (e: Exception){
            ResponseEntity.badRequest().body(e.message)
        }

    }

}

