package com.application.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
class RelCustomerProvider(
    @Id
    var id: IdRelCustomerProvider,
    var customer: CustomerRel,
    var provider: ProviderRel,
    var createdAt: Instant,
    var updatedAt: Instant,
)

data class IdRelCustomerProvider(
    var customer: String,
    var provider: String,
)

class CustomerRel(
    var name: String,
    var lastName: String,
    var phone: String?,
)

class ProviderRel(
    var name: String,
    var slug: String,
    var description: String,
    var category: String,
    var phone: String,
)
