package br.com.newtonpaiva.pi5login.controller

import br.com.newtonpaiva.pi5login.entity.dto.UserDTO
import br.com.newtonpaiva.pi5login.exception.GeneralException
import br.com.newtonpaiva.pi5login.service.CreateUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class CreateUserController {

    @Autowired
    lateinit var createUserService: CreateUserService

    @PostMapping
    fun createUser(@RequestBody userDTO: UserDTO): ResponseEntity<Any> {
        return try {
            createUserService.createUser(userDTO)
            ResponseEntity.ok().body(userDTO)
        } catch (e: GeneralException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}