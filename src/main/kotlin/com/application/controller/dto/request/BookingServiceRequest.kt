package com.application.controller.dto.request

import com.application.domain.objects.BookingService
import java.math.BigDecimal

data class BookingServiceRequest(
    var id: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
) {
    fun toBookingService() =
        BookingService(
            id = id,
            name = name,
            description = description,
            time = time,
            value = value,
        )
}
