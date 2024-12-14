package com.unictive.unictiveUserApplication.domain.dto.common

data class Pagination (
    val totalRecords: Int,
    val currentPage: Int,
    val totalPage: Int,
    val nextPage: Int?,
    val prevPage: Int?
) {
    companion object {
        fun getNextPage(page: Int?, size: Int?, totalPages: Int): Int? {
            return if(page != null && size != null && page < totalPages) page + 1 else null
        }
        fun getPrevPage(page: Int?, size: Int?): Int? {
            return if(page != null && size != null && page > 1) page - 1 else null
        }
    }
}