package com.unictive.unictiveUserApplication.repository

import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity
import com.unictive.unictiveUserApplication.domain.entity.UserEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface HobbyRepository : JpaRepository<HobbyEntity, Int> {
    @Query(value = "SELECT h.name FROM HobbyEntity h WHERE user.idUser = :idUser")
    fun getHobbyByUser(idUser:Int): List<String>

    @Transactional
    fun deleteAllByUser(user: UserEntity)
}