package br.com.newtonpaiva.pi5login.service

import br.com.newtonpaiva.pi5login.entity.dto.LoginRequest
import br.com.newtonpaiva.pi5login.entity.dto.LoginResponse
import br.com.newtonpaiva.pi5login.security.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginService {

    @Autowired
    lateinit var securityService: SecurityService

    fun login(loginRequest: LoginRequest) : LoginResponse {
        val response =  LoginResponse()
        response.token = securityService.login(loginRequest.username, loginRequest.password)
        if(response.token.isBlank()) {
            response.message = "Login ou senha inválidos."
            response.success = false
        } else {
            response.success = true
        }
        return response
    }
}