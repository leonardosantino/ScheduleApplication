package com.application.repository

import com.application.domain.entity.RelCustomerProvider
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RelCustomerProviderRepository : MongoRepository<RelCustomerProvider, String> {
    fun findByCustomerIdAndProviderId(
        customerId: String,
        providerId: String,
    ): Optional<RelCustomerProvider>

    fun findAllByCustomerId(id: String): List<RelCustomerProvider>
}
