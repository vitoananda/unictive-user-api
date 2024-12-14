package com.unictive.unictiveUserApplication.service.impl

import com.unictive.unictiveUserApplication.domain.constant.ConstantVariables
import com.unictive.unictiveUserApplication.domain.dto.request.ReqInsertHobbyDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse
import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity
import com.unictive.unictiveUserApplication.repository.HobbyRepository
import com.unictive.unictiveUserApplication.service.HobbyService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HobbyServiceImpl(
    private val hobbyRepository: HobbyRepository,
) : HobbyService {
    override fun createHobby(request: ReqInsertHobbyDto): BaseResponse<Any> {
        val hobby = HobbyEntity(
            name = request.name,
        )
        hobbyRepository.save(hobby)
        return BaseResponse(message = ConstantVariables.INSERT_DATA_SUCCESS.format("Hobby"))
    }

}