package com.application.repository

import com.application.domain.entity.Provider
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProviderRepository : MongoRepository<Provider, String> {
    fun findBySlug(slug: String): Optional<Provider>
}
