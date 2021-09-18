package br.com.newtonpaiva.pi5login.service


import br.com.newtonpaiva.pi5login.entity.User
import br.com.newtonpaiva.pi5login.entity.dto.UserDTO
import br.com.newtonpaiva.pi5login.exception.GeneralException
import br.com.newtonpaiva.pi5login.repository.UserRepository
import br.com.newtonpaiva.pi5login.security.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateUserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var securityService: SecurityService

    fun createUser(userDTO: UserDTO): UserDTO {

        val queryExist = userRepository.findByUsername(userDTO.username)

        if(queryExist != null) {
            throw GeneralException("Já existe um usuário cadastrado com esse username!")
        }

        val userFull = User()
        userFull.username = userDTO.username
        userFull.password = BCryptPasswordEncoder().encode(userDTO.password)


        userRepository.save(userFull)

        val userQueryAfterSave = userRepository.findByUsername(userDTO.username)

        if(userQueryAfterSave != null){
            return userDTO
        } else {
            throw GeneralException("Não foi possivel salvar o usuário!")
        }

    }
}