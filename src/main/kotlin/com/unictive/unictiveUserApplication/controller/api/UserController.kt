package com.unictive.unictiveUserApplication.controller.api

import com.unictive.unictiveUserApplication.domain.dto.request.ReqInsertUserDto
import com.unictive.unictiveUserApplication.domain.dto.request.ReqUpdateUserDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse
import com.unictive.unictiveUserApplication.domain.dto.response.ResUserListDto
import com.unictive.unictiveUserApplication.service.AuthService
import com.unictive.unictiveUserApplication.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/list")
    fun getListUser(
        @RequestParam("page", required = false) page: Int?,
        @RequestParam("size", required = false) size: Int?,
    ): ResponseEntity<BaseResponse<List<ResUserListDto>>> {
        val result = userService.getListUser(page, size)
        return ResponseEntity.ok(BaseResponse(
            data = result.data,
            pagination = result.pagination
        ))
    }
    @GetMapping("/detail")
    fun getDetailUser(
        @RequestParam("idUser") idUser: Int,
    ): ResponseEntity<BaseResponse<Any>> {
        val res = userService.getDetailUser(idUser)
        return ResponseEntity.ok(res)
    }

    @PostMapping("/create")
    fun createUser(
        @Valid @RequestBody request: ReqInsertUserDto
    ): ResponseEntity<BaseResponse<Any>> {
        val res = userService.createUser(request)
        return ResponseEntity.ok(res)
    }

    @PutMapping("/update")
    fun updateUser(
        @Valid @RequestBody request: ReqUpdateUserDto
    ): ResponseEntity<BaseResponse<Any>> {
        val res = userService.updateUser(request)
        return ResponseEntity.ok(res)
    }

    @DeleteMapping("/delete")
    fun deleteUser(
        @RequestParam("idUser") idUser: Int,
    ): ResponseEntity<BaseResponse<Any>> {
        val response = userService.deleteUser(idUser)
        return ResponseEntity.ok(response)
    }
}

