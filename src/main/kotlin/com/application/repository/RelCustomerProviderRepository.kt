package com.application.repository

import com.application.domain.entity.RelCustomerProvider
import com.application.domain.objects.IdRelCustomerProvider
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RelCustomerProviderRepository : MongoRepository<RelCustomerProvider, IdRelCustomerProvider> {
    fun findAllByIdCustomer(customerId: String): List<RelCustomerProvider>
}
