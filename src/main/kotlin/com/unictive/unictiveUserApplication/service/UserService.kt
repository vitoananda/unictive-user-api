package com.unictive.unictiveUserApplication.service

import com.unictive.unictiveUserApplication.domain.dto.common.PageResponse
import com.unictive.unictiveUserApplication.domain.dto.request.ReqInsertUserDto
import com.unictive.unictiveUserApplication.domain.dto.request.ReqUpdateUserDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse
import com.unictive.unictiveUserApplication.domain.dto.response.ResUserListDto
import org.springframework.data.domain.AbstractPageRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

interface UserService {
    fun getListUser(
        page: Int?,
        size: Int?,
    ): PageResponse<ResUserListDto>
    fun getDetailUser(userId: Int): BaseResponse<Any>

    fun createUser(
        request: ReqInsertUserDto
    ): BaseResponse<Any>

    fun updateUser(
        request: ReqUpdateUserDto
    ): BaseResponse<Any>
}