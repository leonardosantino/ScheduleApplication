package com.application.controller.dto.response

import com.application.domain.entity.Services
import com.application.domain.objects.ServiceAnnouncement
import java.math.BigDecimal
import java.time.Instant

data class ServiceResponse(
    var id: String?,
    var announcement: ServiceAnnouncement,
    var name: String,
    var description: String,
    var time: Int,
    var value: BigDecimal,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
) {
    companion object {
        fun from(service: Services) =
            ServiceResponse(
                id = service.id,
                announcement = service.announcement,
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
