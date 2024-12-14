package com.unictive.unictiveUserApplication.repository

import com.unictive.unictiveUserApplication.domain.entity.HobbyEntity
import com.unictive.unictiveUserApplication.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface HobbyRepository : JpaRepository<HobbyEntity, Int> {
    @Query(value = "SELECT h FROM HobbyEntity h")
    fun getListHobby(): List<HobbyEntity>
}