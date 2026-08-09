package com.application.repository

import com.application.domain.entity.Services
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ServicesRepository : MongoRepository<Services, String> {
    fun findAllByProviderId(id: String): List<Services>
}
