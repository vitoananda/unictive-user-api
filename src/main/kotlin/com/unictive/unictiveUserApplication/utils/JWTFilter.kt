package com.unictive.unictiveUserApplication.utils

import io.jsonwebtoken.Claims
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.unictive.unictiveUserApplication.domain.constant.ConstantVariables
import com.unictive.unictiveUserApplication.exception.DataNotFoundException
import com.unictive.unictiveUserApplication.exception.UnauthorizedException

@Component
class JWTFilter(
    private val jwtUtils: JWTUtils
) : OncePerRequestFilter() {

    private val objectMapper = ObjectMapper()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestPath = request.requestURI
        if (requestPath.startsWith("/v1/api/auth/register") || requestPath.startsWith("/v1/api/auth/login")) {
            filterChain.doFilter(request, response)
            return
        }

        val authorizationHeader = request.getHeader("Authorization")
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            val token = authorizationHeader.substring(7)
            try {
                val claims: Claims = jwtUtils.decodeJWT(token)

                val email = claims.subject
                if (email != null) {
                    val authentication = UsernamePasswordAuthenticationToken(
                        email, null, listOf()
                    )
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                    SecurityContextHolder.getContext().authentication = authentication
                }
            } catch (e: Exception) {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.write("Unauthorized: ${e.message}")
                return
            }
        }

        filterChain.doFilter(request, response)
    }
}


