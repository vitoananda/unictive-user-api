package com.unictive.unictiveUserApplication.repository

import com.unictive.unictiveUserApplication.domain.entity.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findByEmailAndPassword(email: String?, password: String?) : UserEntity?

    @Query(value = "SELECT u FROM UserEntity u WHERE u.idUser = :idUser")
    fun getByIdUser(idUser: Int): UserEntity?

    @Query(
        value = "SELECT u FROM UserEntity u "
                + "ORDER BY u.dtAdded DESC "
    )
    fun getListUser(pageable: Pageable): Page<UserEntity>
}