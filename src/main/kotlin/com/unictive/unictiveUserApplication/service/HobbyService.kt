package com.unictive.unictiveUserApplication.service

import com.unictive.unictiveUserApplication.domain.dto.request.ReqInsertHobbyDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse

interface HobbyService {
    fun createHobby(request: ReqInsertHobbyDto): BaseResponse<Any>
//    fun getListHobby(userId: Int): BaseResponse<Any>
}