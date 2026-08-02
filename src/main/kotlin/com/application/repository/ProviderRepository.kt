package com.application.repository

import com.application.domain.entity.Provider
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProviderRepository : JpaRepository<Provider, String> {
    fun findBySlug(slug: String): Provider
}
