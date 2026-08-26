package com.application.controller.dto.response

import com.application.domain.entity.ServiceProvider
import com.application.domain.entity.Services
import java.math.BigDecimal
import java.time.Instant

data class ServiceResponse(
    var id: String?,
    var provider: ServiceProvider,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
) {
    companion object {
        fun from(service: Services) =
            ServiceResponse(
                id = service.id,
                provider = service.provider,
                name = service.name,
                description = service.description,
                time = service.time,
                value = service.value,
                status = service.status,
                createdAt = service.createdAt,
                updatedAt = service.updatedAt,
            )
    }
}
