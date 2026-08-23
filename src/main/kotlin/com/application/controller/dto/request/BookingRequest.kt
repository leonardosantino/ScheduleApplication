package com.application.controller.dto.request

import com.application.domain.entity.Booking
import java.time.Instant
import java.time.LocalDate

data class BookingRequest(
    var id: String?,
    var user: BookingUserRequest,
    var provider: BookingProviderRequest,
    var service: BookingServiceRequest,
    var date: LocalDate,
    var times: List<Int>,
) {
    fun toCreate() =
        Booking(
            id = null,
            user = user.toBookingUser(),
            provider = provider.toProvider(),
            service = service.toBookingService(),
            date = date.toString(),
            time = times.first(),
            times = times,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
