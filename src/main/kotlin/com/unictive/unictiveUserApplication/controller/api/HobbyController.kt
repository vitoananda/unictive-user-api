package com.unictive.unictiveUserApplication.controller.api

import com.unictive.unictiveUserApplication.service.AuthService
import com.unictive.unictiveUserApplication.service.HobbyService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/hobby")
class HobbyController(
    private val hobbyService: HobbyService
){

}