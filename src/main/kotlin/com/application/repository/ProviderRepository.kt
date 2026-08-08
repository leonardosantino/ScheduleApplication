package com.application.repository

import com.application.domain.entity.Provider
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProviderRepository : MongoRepository<Provider, String> {
    fun findBySlug(slug: String): Provider
}
