package com.application.controller.dto.response

import com.application.domain.entity.Services
import com.application.domain.objects.ServiceProvider
import java.math.BigDecimal

data class ServiceResponse(
    var id: String?,
    var provider: ServiceProvider,
    var name: String,
    var description: String,
    var time: Int,
    var value: BigDecimal,
    var status: String,
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
            )
    }
}
