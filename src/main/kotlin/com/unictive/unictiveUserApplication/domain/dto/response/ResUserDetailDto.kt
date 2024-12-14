package com.unictive.unictiveUserApplication.domain.dto.response

import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity

data class ResUserDetailDto(
    val idUser: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val hobbies: List<HobbyEntity>? = null
)
