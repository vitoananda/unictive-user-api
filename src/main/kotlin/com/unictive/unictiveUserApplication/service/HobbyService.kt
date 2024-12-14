package com.unictive.unictiveUserApplication.service

import com.unictive.unictiveUserApplication.domain.dto.request.ReqInsertHobbyDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse
import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity

interface HobbyService {
    fun getListHobby(): BaseResponse<List<HobbyEntity>>
}