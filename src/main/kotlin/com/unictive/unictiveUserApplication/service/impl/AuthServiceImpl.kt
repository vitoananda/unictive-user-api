package com.unictive.unictiveUserApplication.service.impl

import com.unictive.unictiveUserApplication.domain.common.StatusCode
import com.unictive.unictiveUserApplication.domain.constant.ConstantVariables
import com.unictive.unictiveUserApplication.domain.dto.request.ReqEmailPasswordDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse
import com.unictive.unictiveUserApplication.domain.dto.response.ResLoginDto
import com.unictive.unictiveUserApplication.domain.entity.UserEntity
import com.unictive.unictiveUserApplication.exception.ConflictDataException
import com.unictive.unictiveUserApplication.exception.DataNotFoundException
import com.unictive.unictiveUserApplication.repository.UserRepository
import com.unictive.unictiveUserApplication.service.AuthService
import com.unictive.unictiveUserApplication.utils.JWTUtils
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository
) : AuthService {
    override fun userRegister(request: ReqEmailPasswordDto): BaseResponse<Any> {
        val existingUser = userRepository.findByEmailAndPassword(request.email, request.password)
        if (existingUser != null) {
            throw ConflictDataException(ConstantVariables.DATA_ALREADY_EXIST.format("User"))
        }
        val user = UserEntity(
            email = request.email,
            password = request.password
        )
        userRepository.save(user)

        return BaseResponse(
            message = ConstantVariables.INSERT_DATA_SUCCESS.format("User"),
        )
    }

    override fun userLogin(request: ReqEmailPasswordDto): BaseResponse<Any> {
        val user = userRepository.findByEmailAndPassword(
            request.email,
            request.password
        ) ?: throw DataNotFoundException(ConstantVariables.DATA_NOT_FOUND.format("User"))

        val token = JWTUtils().createJWT(
            user.idUser.toString(),
            request.email!!,
        )

        val response = ResLoginDto(
            idUser = user.idUser.toString(),
            email = user.email,
            token = token

        )

        return BaseResponse(data = response)
    }
}
