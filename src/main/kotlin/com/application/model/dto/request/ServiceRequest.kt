package com.application.model.dto.request

import com.application.domain.entity.Services
import com.application.domain.objects.ServiceStatus
import java.math.BigDecimal
import java.time.Instant

data class ServiceRequest(
    var id: String?,
    var providerId: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
    var status: String?,
    var createdAt: Instant?,
    var updatedAt: Instant?,
) {
    fun toCreate() =
        Services(
            id = null,
            providerId = providerId,
            name = name,
            description = description,
            time = time,
            value = value,
            status = ServiceStatus.ENABLED.value,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
