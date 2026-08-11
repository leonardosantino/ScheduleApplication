package com.application.controller.dto.response

import com.application.domain.objects.BookingService
import java.math.BigDecimal

data class BookingServiceResponse(
    var id: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
) {
    companion object {
        fun from(service: BookingService) =
            BookingServiceResponse(
                id = service.id,
                name = service.name,
                description = service.description,
                time = service.time,
                value = service.value,
            )
    }
}
