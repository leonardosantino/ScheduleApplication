package com.application.controller.dto.response

import com.application.domain.objects.AppointmentService
import java.math.BigDecimal

data class AppointmentServiceResponse(
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
) {
    companion object {
        fun from(service: AppointmentService) =
            AppointmentServiceResponse(
                name = service.name,
                description = service.description,
                time = service.time,
                value = service.value,
            )
    }
}
