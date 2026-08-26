package com.application.controller.dto.request

import com.application.domain.objects.AppointmentService
import java.math.BigDecimal

data class AppointmentServiceRequest(
    var id: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
) {
    fun toService() =
        AppointmentService(
            id = id,
            name = name,
            description = description,
            time = time,
            value = value,
        )
}
