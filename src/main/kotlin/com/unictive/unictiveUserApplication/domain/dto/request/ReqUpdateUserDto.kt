package com.unictive.unictiveUserApplication.domain.dto.request

import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity

data class ReqUpdateUserDto(
    val idUser: Int? =null,
    val name: String? = null,
    val hobby:List<HobbyEntity>? = null
)
