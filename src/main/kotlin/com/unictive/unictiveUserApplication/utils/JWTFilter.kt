package com.unictive.unictiveUserApplication.utils

import io.jsonwebtoken.Claims
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JWTFilter(
    private val jwtUtils: JWTUtils
) : OncePerRequestFilter() {

    private val logger = LoggerFactory.getLogger(JWTFilter::class.java)

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logger.info("Request URL: ${request.requestURL}")
        logger.info("Request Method: ${request.method}")
        logger.info("Authorization Header: ${request.getHeader("Authorization")}")

        val authorizationHeader = request.getHeader("Authorization")

        if (isPublicRoute(request.requestURI)) {
            filterChain.doFilter(request, response)
        } else if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            val jwt = authorizationHeader.substring(7)
            try {
                val claims = jwtUtils.decodeJWT(jwt)
                setAuthenticationContext(claims, request)
                filterChain.doFilter(request, response)
            } catch (e: Exception) {
                logger.error("JWT Validation Error: ${e.message}")
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token")
            }
        } else {
            logger.warn("No JWT token provided for protected route: ${request.requestURI}")
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is required")
        }

    }

    private fun isPublicRoute(uri: String): Boolean {
        return uri.startsWith("/v1/api/auth/") || uri.startsWith("/v1/cms/")
    }

    private fun setAuthenticationContext(claims: Claims, request: HttpServletRequest) {
        val email = claims["email", String::class.java]
        val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))

        val userDetails = User(email, "", authorities)

        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, authorities)
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication

        logger.info("Authentication set for user: $email")
    }
}
