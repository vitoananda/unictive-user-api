package com.unictive.unictiveUserApplication.domain.dto.request

import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity

data class ReqInsertUserDto(
    val name: String? = null,
    val hobby:List<HobbyEntity>? = null
)
