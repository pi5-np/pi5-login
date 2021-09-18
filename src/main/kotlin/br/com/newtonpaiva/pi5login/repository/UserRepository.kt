package br.com.newtonpaiva.pi5login.repository

import br.com.newtonpaiva.pi5login.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, Long> {

    fun findByUsername(username: String): User?

}
