package com.unictive.unictiveUserApplication.domain.dto.common

data class PageResponse<T> (
    val data: List<T>,
    val pagination: Pagination,
)