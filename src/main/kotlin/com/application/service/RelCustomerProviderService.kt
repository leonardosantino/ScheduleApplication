package com.application.service

import com.application.domain.entity.RelCustomerProvider
import com.application.repository.RelCustomerProviderRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class RelCustomerProviderService(
    private val relCustomerProviderRepository: RelCustomerProviderRepository,
) {
    fun findAllByCustomerId(id: String): List<RelCustomerProvider> = relCustomerProviderRepository.findAllByCustomerId(id)

    fun save(rel: RelCustomerProvider) {
        val it =
            relCustomerProviderRepository
                .findByCustomerIdAndProviderId(
                    customerId = rel.customer.id,
                    providerId = rel.provider.id,
                ).map { it.copy(customer = rel.customer, provider = rel.provider, updatedAt = Instant.now()) }
                .orElseGet { rel }

        relCustomerProviderRepository.save(it)
    }
}
