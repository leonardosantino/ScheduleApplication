package com.application.repository

import com.application.domain.entity.Services
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServicesRepository : JpaRepository<Services, Long> {
    fun findAllByUserId(id: String): List<Services>
}
