package br.com.newtonpaiva.pi5login.configuration

import br.com.newtonpaiva.pi5login.entity.dto.UserDTO
import br.com.newtonpaiva.pi5login.exception.GeneralException
import br.com.newtonpaiva.pi5login.service.CreateUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class CommandLineRunner: CommandLineRunner {

    @Autowired
    lateinit var userService: CreateUserService

    override fun run(vararg args: String?) {
        val user1 = UserDTO("usuario_one", "12345")
        val user2 = UserDTO("usuario_two", "12345")
        val user3 = UserDTO("usuario_three", "12345")
        val user4 = UserDTO("usuario_four", "12345")
        val user5 = UserDTO("usuario_five", "12345")
        val user6 = UserDTO("usuario_six", "12345")
        val user7 = UserDTO("usuario_seven", "12345")
        val user8 = UserDTO("usuario_eight", "12345")
        val user9 = UserDTO("tarley", "123")

        try{
            userService.createUser(user1)
        } catch (e: GeneralException){
            println(e.message)
        }
        try{
            userService.createUser(user2)
        } catch (e: GeneralException){
            println(e.message)
        }
        try{
            userService.createUser(user3)
        } catch (e: GeneralException){
            println(e.message)
        }
        try{
            userService.createUser(user4)
        } catch (e: GeneralException){
            println(e.message)
        }
        try{
            userService.createUser(user5)
        } catch (e: GeneralException){
            println(e.message)
        }
        try{
            userService.createUser(user6)
        } catch (e: GeneralException){
            println(e.message)
        }
        try{
            userService.createUser(user7)
        } catch (e: GeneralException){
            println(e.message)
        }
        try{
            userService.createUser(user8)
        } catch (e: GeneralException){
            println(e.message)
        }
        try{
            userService.createUser(user9)
        } catch (e: GeneralException){
            println(e.message)
        }
    }
}