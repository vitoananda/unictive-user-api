package com.unictive.unictiveUserApplication.controller.api

import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse
import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity
import com.unictive.unictiveUserApplication.service.AuthService
import com.unictive.unictiveUserApplication.service.HobbyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/hobby")
class HobbyController(
    private val hobbyService: HobbyService
){
    @GetMapping("/list")
    fun getListHobby(): BaseResponse<List<HobbyEntity>> {
        return hobbyService.getListHobby()
    }
}