package com.unictive.unictiveUserApplication.controller.api

import com.unictive.unictiveUserApplication.domain.dto.request.ReqEmailPasswordDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse
import com.unictive.unictiveUserApplication.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/register")
    fun registerUser(
        @Valid @RequestBody request: ReqEmailPasswordDto
    ): ResponseEntity<BaseResponse<Any>> {
        val res = authService.userRegister(request)
        return ResponseEntity.ok(res)
    }
    @PostMapping("/login")
    fun loginUser(
        @Valid @RequestBody request: ReqEmailPasswordDto
    ): ResponseEntity<BaseResponse<Any>> {
        val res = authService.userLogin(request)
        return ResponseEntity.ok(res)
    }
}