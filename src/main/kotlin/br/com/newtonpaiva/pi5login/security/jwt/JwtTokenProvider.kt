package br.com.newtonpaiva.pi5login.security.jwt

import br.com.newtonpaiva.pi5login.entity.dto.ResponseLoginToken
import br.com.newtonpaiva.pi5login.security.UserPrincipal
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider {

    val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    private var jwtSecret = "MySecret"
    private var jwtExpirationInMs = 860_000_000

    fun generateToken(authentication: Authentication): String? {

        val userPrincipal: UserPrincipal = authentication.principal as UserPrincipal

        val now = Date()
        val exp = Date(now.time + jwtExpirationInMs.toLong())

        return Jwts.builder()
                .setSubject(userPrincipal.username)
                .setIssuedAt(Date())
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact()
    }

    fun getUserIdFromJWT(token: String): ResponseLoginToken? {
        val claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body

        return ResponseLoginToken(
            username = claims["username"].toString()
        )
    }

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }

        return false
    }
}