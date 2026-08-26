package com.application.repository

import com.application.domain.entity.Services
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ServicesRepository : MongoRepository<Services, String> {
    fun findAllByAnnouncementId(id: String): List<Services>

    fun findAllByAnnouncementIdAndStatus(
        id: String,
        status: String,
    ): List<Services>
}
