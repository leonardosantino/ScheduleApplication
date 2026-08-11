package com.application.controller.dto.request

import com.application.domain.entity.Booking
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

data class BookingRequest(
    var id: String?,
    var user: BookingUserRequest,
    var provider: BookingProviderRequest,
    var service: BookingServiceRequest,
    var date: LocalDate,
    var times: List<Double>,
) {
    fun toCreate() =
        Booking(
            id = null,
            user = user.toBookingUser(),
            provider = provider.toProvider(),
            service = service.toBookingService(),
            date = date.atStartOfDay(ZoneOffset.UTC).toInstant(),
            times = times,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
