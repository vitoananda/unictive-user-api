package com.unictive.unictiveUserApplication.domain.dto.response

import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity

data class ResUserListDto(
    val idUser: Int? = null,
    val name: String? = null,
    val hobby: List<String>? = null
)
