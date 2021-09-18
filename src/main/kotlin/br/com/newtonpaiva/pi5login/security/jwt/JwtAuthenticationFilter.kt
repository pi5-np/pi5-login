package br.com.newtonpaiva.pi5login.security.jwt

import br.com.newtonpaiva.pi5login.security.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    lateinit var tokenProvider: JwtTokenProvider

    @Autowired
    lateinit var customUserDetailsService: UserDetailsServiceImpl

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jwt = getJwtFromRequest(request)

            if (jwt.isNotBlank() && tokenProvider.validateToken(jwt)) {
                val userId = tokenProvider.getUserIdFromJWT(jwt)
                userId?.let {
                    val userDetails = customUserDetailsService.loadUserByUsername(userId.username)
                    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails?.authorities)
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        } catch (ex: Exception) {
            logger.error("Could not set user authentication in security context", ex)
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String {
        val bearerToken : String? = request.getHeader("Authorization")
        var token = ""
        bearerToken?.let { bearerToken ->
            if (bearerToken.isNotBlank() && bearerToken.startsWith("Bearer ")) {
                token = bearerToken.substring(7, bearerToken.length)
            }}
        return token
    }

}