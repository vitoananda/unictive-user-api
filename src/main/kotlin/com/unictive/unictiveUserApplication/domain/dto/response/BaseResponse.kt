package com.unictive.unictiveUserApplication.domain.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.unictive.unictiveUserApplication.domain.common.StatusCode
import com.unictive.unictiveUserApplication.domain.constant.ConstantVariables
import com.unictive.unictiveUserApplication.domain.dto.common.Pagination

data class BaseResponse<T>(
    @field:JsonProperty(value = "success")
    val success: Boolean = StatusCode.SUCCESS.code,
    @field:JsonProperty(value = "message")
    val message: String = ConstantVariables.SUCCESS_MESSAGE,
    @field:JsonProperty(value = "data")
    val data: T? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val page: Int? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val totalData: Int? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val pagination: Pagination? = null
)