package com.unictive.unictiveUserApplication.service.impl

import com.unictive.unictiveUserApplication.domain.constant.ConstantVariables
import com.unictive.unictiveUserApplication.domain.dto.common.PageResponse
import com.unictive.unictiveUserApplication.domain.dto.common.Pagination
import com.unictive.unictiveUserApplication.domain.dto.request.ReqInsertUserDto
import com.unictive.unictiveUserApplication.domain.dto.request.ReqUpdateUserDto
import com.unictive.unictiveUserApplication.domain.dto.response.BaseResponse
import com.unictive.unictiveUserApplication.domain.dto.response.ResUserDetailDto
import com.unictive.unictiveUserApplication.domain.dto.response.ResUserListDto
import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity
import com.unictive.unictiveUserApplication.domain.entity.UserEntity
import com.unictive.unictiveUserApplication.exception.DataNotFoundException
import com.unictive.unictiveUserApplication.repository.UserRepository
import com.unictive.unictiveUserApplication.service.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun getListUser(page: Int?, size: Int?): PageResponse<ResUserListDto> {
        val pageReq = if(page != null && size != null) page - 1 else 0
        val sizeReq =  if(page != null && size != null) size else Integer.MAX_VALUE
        val pageable = PageRequest.of(pageReq, sizeReq)

        val users = userRepository.getListUser(pageable)
        val response: ArrayList<ResUserListDto> = ArrayList()

        val sdf = SimpleDateFormat("yyyy-MM-dd kk:mm:ss")
        users.forEach {
            response.add(
                ResUserListDto(
                   idUser = it.idUser,
                    name = it.name,
                    hobby = it.hobbies
                )
            )
        }

        return PageResponse(
            data = response,
            pagination = Pagination(
                totalRecords = if (sizeReq == 1) 1 else users.totalElements.toInt(),
                currentPage = page ?: 1,
                totalPage = users.totalPages,
                nextPage = Pagination.getNextPage(page, size, users.totalPages),
                prevPage = Pagination.getPrevPage(page, size)
            )
        )
    }

    override fun createUser(request: ReqInsertUserDto): BaseResponse<Any> {
        val user = UserEntity(
            name = request.name,
            hobbies = request.hobby!!
        )
        userRepository.save(user)
        return BaseResponse(message = ConstantVariables.INSERT_DATA_SUCCESS.format("User"))
    }
    override fun getDetailUser(userId: Int): BaseResponse<Any> {
        val user = userRepository.getByIdUser(userId)
            ?: throw DataNotFoundException(ConstantVariables.DATA_NOT_FOUND.format("User"))

        val response = ResUserDetailDto(
            idUser = user.idUser,
            name = user.name,
            email = user.email,
            hobbies = user.hobbies
        )

        return BaseResponse(data = response)
    }

    override fun updateUser(request: ReqUpdateUserDto): BaseResponse<Any> {
        val user = userRepository.getByIdUser(request.idUser!!)
            ?: throw DataNotFoundException(ConstantVariables.DATA_NOT_FOUND.format("User"))

        user.name = request.name ?: user.name
        user.hobbies = request.hobby ?: user.hobbies

        userRepository.save(user)

        return BaseResponse(message = ConstantVariables.UPDATE_DATA_SUCCESS.format("User"))
    }
}