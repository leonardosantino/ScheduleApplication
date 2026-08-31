package com.application.service

import com.application.domain.entity.RelCustomerProvider
import com.application.domain.objects.CustomerRel
import com.application.domain.objects.IdRelCustomerProvider
import com.application.domain.objects.ProviderRel
import com.application.repository.RelCustomerProviderRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class RelCustomerProviderService(
    private val relCustomerProviderRepository: RelCustomerProviderRepository,
) {
    fun findAllByCustomerId(customerId: String): List<RelCustomerProvider> = relCustomerProviderRepository.findAllByIdCustomer(customerId)

    fun save(
        id: IdRelCustomerProvider,
        customer: CustomerRel,
        provider: ProviderRel,
    ) {
        relCustomerProviderRepository.save(
            RelCustomerProvider(
                id = IdRelCustomerProvider(id.customer, id.provider),
                customer =
                    CustomerRel(
                        name = customer.name,
                        lastName = customer.lastName,
                        phone = customer.phone,
                    ),
                provider =
                    ProviderRel(
                        name = provider.name,
                        slug = provider.slug,
                        description = provider.description,
                        category = provider.category,
                        phone = provider.phone,
                    ),
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
            ),
        )
    }
}
