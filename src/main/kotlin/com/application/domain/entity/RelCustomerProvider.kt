package com.application.domain.entity

import com.application.domain.objects.CustomerRel
import com.application.domain.objects.ProviderRel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
data class RelCustomerProvider(
    @Id
    var id: String?,
    var customer: CustomerRel,
    var provider: ProviderRel,
    var createdAt: Instant,
    var updatedAt: Instant,
)
