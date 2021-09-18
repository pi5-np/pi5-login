package br.com.newtonpaiva.pi5login.security

import br.com.newtonpaiva.pi5login.entity.User
import br.com.newtonpaiva.pi5login.exception.GeneralException
import br.com.newtonpaiva.pi5login.security.jwt.JwtTokenProvider
import org.jboss.logging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class SecurityService {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userDetailsService: UserDetailsServiceImpl

    @Autowired
    lateinit var tokenProvider: JwtTokenProvider


    val logger = Logger.getLogger(this::class.java)

    fun findLoggedInUsername(): User {
        return (SecurityContextHolder.getContext().authentication.principal as UserPrincipal).user
    }

    fun login(username: String, password: String) : String {
        val userDetails = userDetailsService.loadUserByUsername(username)
        var token = ""
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, password,
            userDetails.authorities
        )
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        } catch (e: Exception){
            throw GeneralException("Usuário ou senha incorretos!")
        }
        if (usernamePasswordAuthenticationToken.isAuthenticated) {
            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            logger.debug(String.format("Auto login %s successfully!", username))
            token = tokenProvider.generateToken(usernamePasswordAuthenticationToken).toString()
        }
        return token
    }
}