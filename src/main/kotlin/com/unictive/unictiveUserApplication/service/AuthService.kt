package com.unictive.unictiveUserApplication.service

import com.unictive.unictiveUserApplication.domain.dto.request.ReqEmailPasswordDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse


interface AuthService {
    fun userRegister(request: ReqEmailPasswordDto) : BaseResponse<Any>
    fun userLogin(request: ReqEmailPasswordDto) : BaseResponse<Any>
}