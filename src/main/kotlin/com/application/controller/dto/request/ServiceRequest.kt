package com.application.controller.dto.request

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
    var status: String,
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

    fun toUpdate(service: Services): Services {
        service.name = name
        service.description = description
        service.time = time
        service.value = value
        service.status = status

        service.updatedAt = Instant.now()

        return service
    }
}
