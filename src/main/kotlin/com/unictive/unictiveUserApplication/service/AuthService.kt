package com.unictive.unictiveUserApplication.service

import com.unictive.unictiveUserApplication.domain.dto.request.ReqLoginDto
import com.unictive.unictiveUserApplication.domain.dto.request.ReqRegisterDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse


interface AuthService {
    fun userRegister(request: ReqRegisterDto) : BaseResponse<Any>
    fun userLogin(request: ReqLoginDto) : BaseResponse<Any>
}